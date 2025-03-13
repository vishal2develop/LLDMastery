import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;

    public Board(int boardSize, int numberOfSnakes, int numberOfLadders) {
        initializeCells(boardSize);
        addSnakeLadders(cells,numberOfSnakes,numberOfLadders);

    }

    public void initializeCells(int boardSize){
        cells=new Cell[boardSize][boardSize];

        for (int i=0;i<boardSize;i++){
            for (int j=0;j<boardSize;j++){
                Cell cellObj = new Cell();
                cells[i][j] = cellObj;
            }
        }
    }

    public void addSnakeLadders(Cell[][] cells,int noOfSnakes,int noOfLadders){
        while(noOfSnakes>0){
            int snakeHead = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int snakeTail = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(snakeTail >= snakeHead) {
                continue;
            }

            Jump snakeObj = new Jump();
            snakeObj.start = snakeHead;
            snakeObj.end = snakeTail;

            Cell cell = getCell(snakeHead);
            cell.jump = snakeObj;

            noOfSnakes--;

        }

        while(noOfLadders>0){
            int ladderStart = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            int ladderEnd = ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(ladderStart >= ladderEnd) {
                continue;
            }

            Jump ladderObj = new Jump();
            ladderObj.start = ladderStart;
            ladderObj.end = ladderEnd;

            Cell cell = getCell(ladderStart);
            cell.jump = ladderObj;

            noOfLadders--;

        }

    }

    public Cell getCell(int position){
        int boardRow = position/cells.length;
        int boardColumn = (position%cells.length);
        return cells[boardRow][boardColumn];
    }
}
