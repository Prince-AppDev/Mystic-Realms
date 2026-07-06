// main game logic
enum class ItemType
{
    HEALING ,
    WEAPON
}

enum class Location
{
    DARK_FOREST,
    SKELETON_CAVE,
    HAUNTED_RUINS,
    ZOMBIE_VILLAGE
}
enum class AttackTypes(val displayName : String , val multiplier : Double) {
    QUICK_STRIKE("Quick Strike💨", 0.8),
    POWER_SMASH("Power Smash💪", 1.5),
    DARK_CURSE("Dark Curse 💥", 1.8),
    SAVAGE_RAGE("Savage Rage ☢️", 2.0)
}
data class Item(
    val name: String,
    val itemType: ItemType,
    val healAmount :Int =0,
    val attackDamage : Int =0
)


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
    var experience : Int = 0,
    // add items in player class
    val inventory : MutableList<Item> =mutableListOf()
)

fun showIntro() {
    println(
        """              
                      ⚔️ MYSTIC REALMS V2⚔️
        <====================================================>
           Long ago, the kingdom of Eldoria lived in peace.

                 But darkness has begun to spread...
                    Monsters roams the forests.
             and fear grips the hearts of the Village.

                   Only one question remains...
           Who will rise to challenge the coming darkness ?

                  Welcome, brave adventurer !
               The Kingdom of Eldoria needs a Hero 🏇
        <====================================================> """)
    println()
}

fun askPlayerName() : String {
    var name: String
    while (true) {
        println("              What is your name adventurer ? ")

        name = readlnOrNull() ?:""
        if (name.isBlank()) {
            println("⚠️ Please Enter Your Name First ")
            println()
        }
        else {
            break
        }
    }
    return name
}

fun showWelcome(name: String) {
    println()
    println("                  Welcome to Eldoria , $name !")
    println("                Your Journey is about to begin.")
}

fun villageElderDialogue():Quest
{
  println("════════════════════════════════════════════════════════════════════════════════════")
    println("Village Elder:")
    println("Dark creatures have appeared near our village.\nPlease defeat the goblin in the Forest🌲")

    return Quest(
        description = "Defeat the Goblin 👺" ,
        targetLocation = Location.DARK_FOREST,
        monsterName = "Goblin"
    )
}
fun showLocations()
{
    println()
    println("<═════════════════════Where Do You Want To Explore 🗺️═════════════════════════>")
    println("1. Dark Forest 🌳")
    println("2. Skeleton Cave ⛰️")
    println("3. Haunted Ruins 👻 ")
    println("4. Zombie Village 🧟‍♂️")
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
            health = 100 ,
            attackPower = 12,
            reward = 30
        )
        Location.SKELETON_CAVE -> Monster(
            name = "Bone Archer",
            health = 120 ,
            attackPower = 14,
            reward = 50
        )
        Location.HAUNTED_RUINS -> Monster(
            name = "Shadow Wraith",
            health = 150 ,
            attackPower = 16,
            reward = 60
        )
        Location.ZOMBIE_VILLAGE -> Monster(
            name = "Zombie Brute",
            health = 200 ,
            attackPower = 20,
            reward = 70
        )
    }
}
fun showCombatOptions(player: Player)
{
    println()
    println("  ════════ Your Turn ════════")
    println("1.⚔️Sword")
    println("2.🔥Fireball")
    println("3. ⚡Lighting")
    println("4.🎒Open Inventory(${player.inventory.size} Items)")
    println()
    println("Choose your Action(1-4) :")
}
fun showInventory(player: Player) {
 println()
    println("         INVENTORY        ")
    drawSeparator()
    if(player.inventory.isEmpty())
    {
        println("Bag is Empty 😔" +
                " !")
        return
    }
    player.inventory.forEachIndexed { index, item ->
        when(item.itemType)
        {
           ItemType.HEALING -> println("${index+1}.${item.name} -> +${item.healAmount} HP")
            ItemType.WEAPON -> println("${index+1}.${item.name} -> +${item.attackDamage} Damage")
        }
    }
    println("0 Cancel")
    println()
    println("Choose Item(0-${player.inventory.size} Items)")
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
    val attackType = AttackTypes.entries.random()
    val damage =  (monster.attackPower*attackType.multiplier).toInt()
    println()
    println("${monster.name} Use Attack Type : ${attackType.displayName}!")
    println("Dealt $damage Damage!")
    val updatedPlayer = player.copy(health = player.health-damage )

    return updatedPlayer
}
fun checkLevelUp(player: Player):Player {
    val levelThresholds = listOf(0, 50, 100, 200)
    val nextLevel = player.level + 1
    if (nextLevel > levelThresholds.size) return player
    val xpNeeded = levelThresholds[player.level]
    return if (player.experience >= xpNeeded) {
        val newAttack = player.attackPower + 10
        val maxNewHp = player.maxHealth + 50
        println("<══════════════════════════════════════════════════════════════════════════>")
        println("  Level UP : ${player.name} is now Level $nextLevel")
        println("  Attack   : ${player.attackPower} ➡️ $newAttack")
        println("  Max HP   : ${player.maxHealth}   ➡️ $maxNewHp")
        val updatedPlayer = player.copy(
            level = nextLevel,
            attackPower = newAttack,
            maxHealth = maxNewHp,
            health = maxNewHp
        )
        when (nextLevel) {
            2 -> {
                updatedPlayer.inventory.add(
                    Item("💊Super Potion", ItemType.HEALING, healAmount = 60)
                )
                updatedPlayer.inventory.add(
                    Item("🌑Energy Ball", ItemType.WEAPON, attackDamage = 50)
                )
                println("🤩Reward Super Potion + Bomb added !")
            }

            3 -> {
                updatedPlayer.inventory.add(
                    Item("✨Elixir", ItemType.HEALING, healAmount = 100)
                )
                updatedPlayer.inventory.add(
                    Item("🪄Magic Staff", ItemType.WEAPON, attackDamage = 60)
                )
                println("🎁Reward Elixir + Magic Staff added !")
            }
        }

        return updatedPlayer
    }
        else
        {
            println("XP : ${player.experience}/${xpNeeded}")
            player
        }
    }

    fun combatLoop(player: Player, monster: Monster): Player {
        var currentPlayer = player
        var monsterHealth = monster.health
        while (currentPlayer.health > 0 && monsterHealth > 0) {

            drawSeparator()
            println("${currentPlayer.name}")
            println("HP" + drawHpBar(currentPlayer.health, currentPlayer.maxHealth))
            println()
            println(
                drawXpBar(
                    currentPlayer.experience, when (currentPlayer.level) {
                        1 -> 50
                        2 -> 100
                        3 -> 200
                        else -> 200
                    }, currentPlayer.level
                )
            )
            println()
            println("${monster.name}💀")
            println("HP" + drawHpBar(monsterHealth, monster.health))
            drawSeparator()

            showCombatOptions(currentPlayer)


            val choice = readLine() ?: "1"
            val damage = when (choice) {

                "1" -> playerAttack("⚔️Attack") { currentPlayer.attackPower }
                "2" -> playerAttack("🔥Fireball") { 25 }
                "3" -> playerAttack(" ⚡Lighting") { 20 }
                "4" -> {
                    showInventory(currentPlayer)
                    val itemChoice = readLine()?.toIntOrNull() ?: 0
                    if (itemChoice == 0 || itemChoice > currentPlayer.inventory.size) {
                        println("Cancelled !")
                        0
                    } else {
                        val selectedItem = currentPlayer.inventory[itemChoice - 1]
                        currentPlayer.inventory.removeAt(itemChoice - 1)

                        when (selectedItem.itemType) {
                            ItemType.HEALING -> {
                                currentPlayer = currentPlayer.copy(
                                    health = (currentPlayer.health + selectedItem.healAmount)
                                        .coerceAtMost(currentPlayer.maxHealth)
                                )

                                println("Used ${selectedItem.name}!")
                                println("HP" + drawHpBar(currentPlayer.health, currentPlayer.maxHealth))
                                0
                            }

                            ItemType.WEAPON -> {
                                println("Used ${selectedItem.name}!")
                                selectedItem.attackDamage
                            }
                        }
                    }
                }

                else -> {
                    println("Invalid choice ❌ Please Enter (1-4) only ")
                    0
                }
            }

            monsterHealth -= damage
            if (monsterHealth <= 0) {
                println()
                println("Congratulation 🎉 You defeated ${monster.name}!")
                println("You earned 💰 ${monster.reward} XP")
                currentPlayer = currentPlayer.copy(
                    experience = currentPlayer.experience + monster.reward
                )
                currentPlayer = checkLevelUp(currentPlayer)
            } else {
                currentPlayer = monsterAttack(monster, currentPlayer)
            }
        }
        return currentPlayer
    }

