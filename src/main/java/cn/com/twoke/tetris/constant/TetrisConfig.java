package cn.com.twoke.tetris.constant;


public interface TetrisConfig {

	int TILE_WIDTH = 20;
	int TILE_HEIGHT = 20;
	int TILE_INTERVAL = 1;

	int COL = 10;
	int ROW = 20;
	
	int BORDER_WIDTH  = 4;
	int MARGIN_WIDTH  = 5;
	
	
	int START_X = 2;
	int START_Y = 2;
	
	int NEXT_PANEL_OFFSET_X = 10 + TILE_WIDTH * COL  + TILE_INTERVAL * (COL - 1) + MARGIN_WIDTH * 2 + BORDER_WIDTH * 2;
	
}
