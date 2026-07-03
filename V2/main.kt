
fun main() {
    do {
        showIntro()

        val playerName = askPlayerName()
        val player = Player(name = playerName)

        player.inventory.add(Item("🍾Health Potion", ItemType.HEALING, healAmount = 30))
        player.inventory.add(Item("💊Regeneration Potion", ItemType.HEALING, healAmount = 60))
        player.inventory.add(Item("🏹Arrow", ItemType.WEAPON, attackDamage = 25))
        player.inventory.add(Item("💣Bomb", ItemType.WEAPON, attackDamage = 40))



        showWelcome(playerName)
        val currentQuest = villageElderDialogue()
        println()
        println("               Current Quest 🎯 ${currentQuest.description}")
        println()
        println("               Player Stats  ➡️  HP : ${player.health} | Attack : ${player.attackPower} | Level : ${player.level}")

        showLocations()
        val chosenLocation = chooseLocation()
        println("               You Choose To Explore : $chosenLocation")
        println()
        println(getExploreMessage(chosenLocation))
        val monster = spawnMonster(chosenLocation)
        println("A Wild ${monster.name} Appears 🧌!")
        println("${monster.name} HP : ${monster.health} ")
        println("Attack Power : ${monster.attackPower}")

        var finalPlayer = combatLoop(player, monster)

        while (finalPlayer.health > 0) {
            println()
                    println("Continue Exploring (yes/no) 📍")
                val explore = readlnOrNull()?.lowercase() ?: "no"

            if (explore != "yes" && explore !="y") break
          showLocations()
          val nextLocation = chooseLocation()
            println(getExploreMessage(nextLocation))
            val nextMonster = spawnMonster(nextLocation)
            println("⚔️A Wild ${nextMonster.name} Appears !")
            finalPlayer = combatLoop(finalPlayer,nextMonster)
        }

    if (finalPlayer.health <= 0) {
        println()
        println("<════════════════ Game Over ════════════════════>")
        println()
        println("Hero has fallen...")
        println("Darkness Spreads across Eldoria The Monsters have Won 😔.")
        println("════════════════════════════════════════════════════════════")
        drawSeparator("Your Journey ")
        println("Name : ${finalPlayer.name}")
        println("Final Level : ${finalPlayer.level}")
        println("Attack Power :${finalPlayer.attackPower}")
        println("Total XP : ${finalPlayer.experience}")
    } else {
        println("<════════════════════🏆 Victory 🏆════════════════════>")
        println("Eldoria  is Saved !")
        println("The Darkness Begin to Fade...")
        println()
        drawSeparator("Your Journey ")
        println("Name : ${finalPlayer.name}")
        println("Final Level : ${finalPlayer.level}")
        println("Attack Power :${finalPlayer.attackPower}")
        println("Total XP : ${finalPlayer.experience}")
    }

    println("<══════════════════════════════════════════════════════════════>")
    println("Play Again yes/no) 🔄️ : ")
    val again = readlnOrNull()?.lowercase() ?: "no"
}
    while (again=="yes" || again=="Y" || again=="y")
    println("🥰Thanks for playing Mystic Realms !")
}



