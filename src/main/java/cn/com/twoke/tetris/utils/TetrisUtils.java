package cn.com.twoke.tetris.utils;


import static cn.com.twoke.tetris.constant.TetrisColor.COLORS;
import static cn.com.twoke.tetris.constant.TetrisConfig.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import cn.com.twoke.tetris.enums.CalcBlockHandler;
import cn.com.twoke.tetris.enums.TetrisEnum;

public class TetrisUtils {

	public static void drawTile(Graphics2D g,int x, int y, int w, int h, int borderWidth, int colorIndex) {
		g.setColor(COLORS[colorIndex][0]);
		g.fillRect(x, y, w, h);
		g.setStroke(new BasicStroke(borderWidth));
		g.setColor(COLORS[colorIndex][1]);
		g.drawRect(x + borderWidth / 2, y + borderWidth / 2, w - borderWidth, h  - borderWidth);
	}
	
	public static void drawTile(Graphics2D g,int x, int y, int colorIndex) {
		drawTile(g, x, y, 0, 0, colorIndex);
	}
	
	public static void drawTile(Graphics2D g,int x, int y, int offsetX, int offsetY, int colorIndex) {
		drawTile(g, START_X + offsetX + (BORDER_WIDTH + MARGIN_WIDTH) + x * (TILE_WIDTH + TILE_INTERVAL),
				START_Y + offsetY + (BORDER_WIDTH + MARGIN_WIDTH) + y * (TILE_WIDTH + TILE_INTERVAL), TILE_WIDTH, TILE_HEIGHT, 2, colorIndex);
	}
	

	
	
	
	public static void drawContainer(Graphics2D g, int startX, int startY, int col, float row) {
		g.setColor(Color.BLACK);
		g.setStroke(new BasicStroke(BORDER_WIDTH));
		g.drawRect(startX, startY, TILE_WIDTH * col  + TILE_INTERVAL * (col - 1) + MARGIN_WIDTH * 2 + BORDER_WIDTH * 2,
				(int)(TILE_HEIGHT * row + TILE_INTERVAL * (row - 1)  + MARGIN_WIDTH * 2 + BORDER_WIDTH * 2 ));
		g.setStroke(new BasicStroke(1));
		g.drawRect(startX + BORDER_WIDTH + MARGIN_WIDTH / 2,
				startY + BORDER_WIDTH + MARGIN_WIDTH / 2,
				TILE_WIDTH * col  + TILE_INTERVAL * (col - 1) + MARGIN_WIDTH,
				(int)(TILE_HEIGHT * row + TILE_INTERVAL * (row - 1) + MARGIN_WIDTH));
	}
	
	
	
}
