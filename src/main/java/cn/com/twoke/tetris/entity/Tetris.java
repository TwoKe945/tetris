package cn.com.twoke.tetris.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.com.twoke.tetris.constant.TetrisConfig;
import cn.com.twoke.tetris.enums.CalcBlockHandler;
import cn.com.twoke.tetris.enums.TetrisEnum;
import cn.com.twoke.tetris.utils.TetrisUtils;

public class Tetris implements KeyListener {
	
	private final int[][] blocks;
	private int x;
	private int y;
	private final int color;
	private final TetrisEnum type;
	
	private static final int X = 0;
	private static final int Y = 1;
	private int fpsStep = 100;
	private int fps = 0;
	private int maxX;
	private int maxY;
	private int minX;
	private int minY;
	private int turnCount = 0;
	private CalcBlockHandler handler;
	
	public TetrisEnum getType() {
		return type;
	}
	
	public int[][] getBlocks() {
		return blocks;
	}

	public int getColor() {
		return color;
	}
	
	public void addY(int value) {
		this.y += value;
	}

	public Tetris(int x, int y, int color, TetrisEnum type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.color = color;
		this.blocks = new int[type.size][2];
		this.maxX = x;
		this.maxY = y;
		this.minX = x;
		this.minY = y;
		this.handler = type.handler;
		updatePos();
	}
	
	private void updatePos() {
		Point tempPoint;
		int maxX = x;
		int maxY = y;
		int minX = x;
		int minY = y;
		Point[] points = new Point[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			tempPoint = this.handler.calc(i, x, y);
			points[i] = tempPoint;
			if (minX > tempPoint.x) {
				minX = tempPoint.x;
			}
			if (minY > tempPoint.y) {
				minY = tempPoint.y;
			}
			if (maxX < tempPoint.x) {
				maxX = tempPoint.x;
			}
			if (maxY < tempPoint.y) {
				maxY = tempPoint.y;
			}
		}
		if (minX >= 0 && maxX < TetrisConfig.COL &&
				minY >= 0 && minY < TetrisConfig.ROW) {
			for (int i = 0; i < points.length; i++) {
				blocks[i][X] = points[i].x;
				blocks[i][Y] = points[i].y;
			}
			this.maxX = maxX;
			this.maxY = maxY;
			this.minX = minX;
			this.minY = minY;
		}
	}
	
	public void update() {
		if (pressedLeft || pressedRight || pressedDown) {
			updatePos();
		} else {
			if (fps > fpsStep) {
				if (!downOnce) {
					y++;
				} else {
					downOnce = false;
				}
				updatePos();
				fps = 0;
			}
			fps++;
		}
	}
	
	public boolean canMove(int[][] grids) {
		if (maxY + 1 >= grids.length) {
			return false;
		}
		for (int i = 0; i < blocks.length; i++) {
			if (grids[blocks[i][1] + 1][blocks[i][0]] != 0) {
				return false;
			}
		}
		
		return true;
	}

	public int getMaxX() {
		return maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public int getMinX() {
		return minX;
	}

	public int getMinY() {
		return minY;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	private boolean pressedLeft;
	private boolean pressedRight;
	private boolean pressedDown;
	private boolean pressedUp;
	private boolean downOnce;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pressedLeft = true;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pressedRight = true;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pressedDown = true;
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			pressedUp = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT && pressedLeft) {
			if (minX - 1 >= 0) {
				x--;	
			}
			pressedLeft = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && pressedRight) {
			if (maxX + 1 < TetrisConfig.COL) {
				x++;	
			}
			pressedRight = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && pressedDown) {
			if (maxY + 1 < TetrisConfig.ROW) {
				y++;
				downOnce = true;
			}
			pressedDown = false;
		} else if (e.getKeyCode() == KeyEvent.VK_UP&& pressedUp) {
			turnCount++;
			CalcBlockHandler[] handlers = TetrisEnum.turnMap.get(type);
			this.handler  = handlers[turnCount % handlers.length];
			pressedUp = false;
		}
	}

	public Point calcPoint(int idx, int x, int y) {
		return handler.calc(idx, x, y);
	}
	
	public void draw(Graphics2D g) {
		for (int i = 0; i < blocks.length; i++) {
			TetrisUtils.drawTile((Graphics2D)g, blocks[i][0], blocks[i][1],TetrisConfig.PLAYING_PANEL_OFFSET_X,0, color);
		}
	}

	public void swap(int i, int j) {
		this.x = i;
		this.y = j;
	}
	
}
