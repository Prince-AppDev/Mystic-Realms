
enum class Location
{
    DARK_FOREST,
    SKELETON_CAVE,
    HAUNTED_RUINS,
    ZOMBIE_VILLAGE
}
data class Quest(
    val description:String,
    val targetLocation : Location,
    val monsterName : String,
)
data class Monster(
    val name : String,
    val health : Int,
    val attackPower :Int,
    val reward : Int
)
data class Player(
    val name : String,
    var health : Int =100,
    var maxHealth : Int=100,
    var attackPower: Int=15,
    var level : Int = 1,
    var experience : Int = 0
)

    fun showIntro() {
        println(
            """
                      ⚔️ MYSTIC REALMS ⚔️
        ====================================================
           Long ago, the kingdom of Eldoria lived in peace.
 
                 But darkness has begun to spread...
                    Monsters roams the forests.
             and fear grips the hearts of the Village.
    
                   Only one question remains...
           Who will rise to challenge the coming darkness ?
    
                  Welcome, brave adventurer !
               The Kingdom of Eldoria needs a Hero.
        ==================================================== """)
        println()
    }

    fun askPlayerName() : String {
        println("                  What is your name adventurer?        ")
        val name = readLine()?:"Unknown"
        return name
    }

    fun showWelcome(name: String) {
        println()
        println("                  Welcome to Eldoria , $name !")
        println("                Your Journey is about to begin.")
    }

    fun villageElderDialogue():Quest
    {
        println()
        println("                     Village Elder:")
        println("Dark creatures have appeared near our village.")
        println("Please defeat the goblin in the Forest🌲")
        return Quest(
            description = "Defeat the Goblin 👺" ,
            targetLocation = Location.DARK_FOREST,
            monsterName = "Goblin"
        )
    }
fun showLocations()
{
    println()
    println("                  ====WHERE DO YOU WANT TO EXPLORE 🗺️===")
    println("1. Dark Forest ")
    println("2. Skeleton Cave ")
    println("3. Haunted Ruins")
    println("4. Zombie Village ")
    println()
    println("Enter Your Choice(1-4):")
}
fun chooseLocation() : Location
{
    val choice = readLine()?:"1"
    return when (choice) {
        "1" -> Location.DARK_FOREST
        "2" -> Location.SKELETON_CAVE
        "3" -> Location.HAUNTED_RUINS
        "4" -> Location.ZOMBIE_VILLAGE
        else -> {
            println("Invalid choice ! Going to the DARK FOREST")
            Location.DARK_FOREST
        }
    }
}

fun spawnMonster(location: Location) : Monster
{
    return when(location)
    {
        Location.DARK_FOREST -> Monster(
            name = "Goblin",
            health = 80 ,
            attackPower = 12,
            reward = 20
        )
        Location.SKELETON_CAVE -> Monster(
            name = "Bone Archer",
            health = 120 ,
            attackPower = 15,
            reward = 30
        )
        Location.HAUNTED_RUINS -> Monster(
            name = "Shadow Wraith",
            health = 150 ,
            attackPower = 18,
            reward = 40
        )
        Location.ZOMBIE_VILLAGE -> Monster(
            name = "Zombie Brute",
            health = 200 ,
            attackPower = 20,
            reward = 50
        )
    }
}
fun showCombatOptions()
{
    println()
    println("          =================Your Turn================")
    println("1.⚔️Attack")
    println("2.🔥Fireball")
    println("3. ⚡Lighting")
    println("4.🍾Use Potion")
    println()
    println("Choose your Action(1-4) :")
}
fun getExploreMessage(location: Location) :String
{
    return when(location)
    {
        Location.DARK_FOREST -> "You enter the Dark Forest... The Goblin must be here👺!"
        Location.SKELETON_CAVE -> "You venture into Skeleton Cave on your own... No quest here, just danger ahead ⚠️!"
        Location.HAUNTED_RUINS -> "You venture into Haunted Ruins your own... No quest here, just danger ahead ⚠️!"
        Location.ZOMBIE_VILLAGE-> "You venture into Zombie Village on your own... No quest here, just danger ahead ⚠️!"
    }
}

fun playerAttack(attackType : String , action:()->Int):Int {
    val damage =action()
    println("You Used $attackType and dealt $damage damage!")
    return damage
}

fun monsterAttack(monster: Monster , player: Player) :Player
{
    val damage =  monster.attackPower
    println()
    println(" ${monster.name} Attacks For You Damage $damage Damage")
    val updatedPlayer = player.copy(health = player.health-damage )
    println()
    println("  Your HP : ${updatedPlayer.health}/${updatedPlayer.maxHealth}")
    return updatedPlayer
}
fun combatLoop(player: Player , monster: Monster) :Player {
    var currentPlayer = player
    var monsterHealth = monster.health
    while (currentPlayer.health >0 && monsterHealth > 0)
   {
        showCombatOptions()
        val choice = readLine() ?: "1"
        val damage = when (choice) {

            "1" -> playerAttack("⚔️Attack") { currentPlayer.attackPower }
            "2" -> playerAttack("🔥Fireball") { 25 }
            "3" -> playerAttack("⚡Lighting") { 20 }
            "4" -> {
                print("You use a Potion Healed 50 HP")
                currentPlayer = currentPlayer.copy(
                    health = (currentPlayer.health + 50)
                        .coerceAtMost(currentPlayer.maxHealth)
                )
                0
            }

            else -> {
                print("Invalid Choice!")
                0
            }
        }

        monsterHealth -= damage
        if (monsterHealth <= 0) {
            println()
            print("Congratulation 🎉 You defeated ${monster.name}!")
            print("You earned 🪙 ${monster.reward} XP.")
            currentPlayer = currentPlayer.copy(
                experience = currentPlayer.experience + monster.reward)
        } else {
            currentPlayer = monsterAttack(monster, currentPlayer)
        }
    }
        return currentPlayer
    }

fun main() {
    do {
        showIntro()

        val playerName = askPlayerName()
        val player = Player(name = playerName)
        showWelcome(playerName)
        val currentQuest = villageElderDialogue()
        println()
        println("                  Current Quest : ${currentQuest.description}")
        println()
        println("             PLAYER STATS = HP : ${player.health} | Attack : ${player.attackPower} | Level : ${player.level}")

        showLocations()
        val chosenLocation = chooseLocation()
        println("                You Choose To Explore : $chosenLocation")
        println()
        println(getExploreMessage(chosenLocation))
        val monster = spawnMonster(chosenLocation)
        println("A Wild ${monster.name} Appears !")
        println("${monster.name} HP : ${monster.health} ")
        println("Attack Power : ${monster.attackPower}")

        val finalPlayer = combatLoop(player, monster)
        println()
        if (finalPlayer.health <= 0) {
            println("                Game Over ! Eldoria Falls Into Darkness...")
        } else {
            println("🏆You Survived ! Total XP : ${finalPlayer.experience}")
        }
        println("==========================================================")
        println("Play Again yes/no) : ")
        val again = readlnOrNull()?.lowercase() ?: "no"
    }
   while (again=="yes" || again=="Y" || again=="y")
println("Thanks for playing Mystic Realms !")
}



