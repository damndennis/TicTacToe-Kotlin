import java.util.Scanner
// You can experiment here, it won’t be checked

fun main(args: Array<String>) {
  // put your code here
    val scanner = Scanner(System.`in`)

    val winningPositions = mutableListOf<List<Pair<Int, Int>>>()

    // Vertical winning positions
    for (i in 0 until 3) {
        winningPositions.add(
            listOf (
                Pair(0, i), Pair(1, i), Pair(2, i)
            )
        )
    }

    // Horizontal winning positions
    for (i in 0 until 3) {
        winningPositions.add(
            listOf(
                Pair(i, 0), Pair(i, 1), Pair(i, 2)
            )
        )
    }

    // Diagonal winning positions

    winningPositions.add(
        listOf(
            Pair(0, 0), Pair (1, 1), Pair(2, 2)
        )
    )

    winningPositions.add(
        listOf(
            Pair(0, 2), Pair (1, 1), Pair(2, 0)
        )
    )


    val boardMatrix = MutableList(3) { MutableList(3) {' '} }


    printBoard(boardMatrix)
    var turn = 1

   while (true) {
       val xyCoordinate = scanner.nextLine().split(" ")

       // Checks if user inputer 2 digits
       if (xyCoordinate.size != 2) {
           println("You should enter two numbers!")
           continue
       }
       
       val xCheckNull = xyCoordinate[0].toIntOrNull()
       val yCheckNull = xyCoordinate[1].toIntOrNull()


       // Checks if user inputs digits
       if (xCheckNull == null || yCheckNull == null) {
                println("You should enter numbers!")
                continue
            }
       val xCoordinate = xCheckNull.toInt() - 1
       val yCoordinate = yCheckNull.toInt() - 1

       when {
           // Checks if user's coordinates are in bound the matrix
           xCoordinate !in 0..2 || yCoordinate !in 0..2 -> {
               println("Coordinates should be from 1 to 3!")
               continue
           }
           // Checks if user's target cell is occupied
           boardMatrix[xCoordinate][yCoordinate] != ' ' -> {
               println("This cell is occupied! Choose another one!")
               continue
           }
           turn % 2 != 0 -> {
               boardMatrix[xCoordinate][yCoordinate] = 'X'
           }
           turn % 2 == 0 -> {
               boardMatrix[xCoordinate][yCoordinate] = 'O'
           }

       }

       printBoard(boardMatrix)
       turn += 1

       // Checks if a player won
       when {
           winningPositions.any { pos -> pos.all { (x, y) -> boardMatrix[x][y] == 'X' } } -> {
               println("X wins")
               return
           }
           winningPositions.any { pos -> pos.all { (x, y) -> boardMatrix[x][y] == 'O' } } -> {
               println("O wins")
               return
           }
           turn == 10 -> {
               println("Draw")
               return
           }
       }

    }


}


// FUNCTION TO PRINT BOARD
fun printBoard(boardMatrix: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${boardMatrix[0][0]} ${boardMatrix[0][1]} ${boardMatrix[0][2]} |")
    println("| ${boardMatrix[1][0]} ${boardMatrix[1][1]} ${boardMatrix[1][2]} |")
    println("| ${boardMatrix[2][0]} ${boardMatrix[2][1]} ${boardMatrix[2][2]} |")
    println("---------")
}