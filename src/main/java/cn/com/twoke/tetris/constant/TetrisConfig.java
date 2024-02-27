package cn.com.twoke.tetris.constant;

import static cn.com.twoke.tetris.constant.TetrisConfig.BORDER_WIDTH;
import static cn.com.twoke.tetris.constant.TetrisConfig.MARGIN_WIDTH;
import static cn.com.twoke.tetris.constant.TetrisConfig.TILE_INTERVAL;
import static cn.com.twoke.tetris.constant.TetrisConfig.TILE_WIDTH;

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
	int PLAYING_PANEL_OFFSET_X = 10 + TILE_WIDTH * 6  + TILE_INTERVAL * (6 - 1) + MARGIN_WIDTH * 2 + BORDER_WIDTH * 2;
	int NEXT_PANEL_OFFSET_X = PLAYING_PANEL_OFFSET_X + 10 + TILE_WIDTH * COL  + TILE_INTERVAL * (COL - 1) + MARGIN_WIDTH * 2 + BORDER_WIDTH * 2;
	
}
