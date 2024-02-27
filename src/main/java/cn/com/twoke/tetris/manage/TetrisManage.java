package cn.com.twoke.tetris.manage;

import static cn.com.twoke.tetris.constant.TetrisConfig.BORDER_WIDTH;
import static cn.com.twoke.tetris.constant.TetrisConfig.MARGIN_WIDTH;
import static cn.com.twoke.tetris.constant.TetrisConfig.START_X;
import static cn.com.twoke.tetris.constant.TetrisConfig.START_Y;
import static cn.com.twoke.tetris.constant.TetrisConfig.TILE_HEIGHT;
import static cn.com.twoke.tetris.constant.TetrisConfig.TILE_INTERVAL;
import static cn.com.twoke.tetris.constant.TetrisConfig.TILE_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import cn.com.twoke.tetris.constant.TetrisConfig;
import cn.com.twoke.tetris.entity.Tetris;
import cn.com.twoke.tetris.enums.TetrisEnum;
import cn.com.twoke.tetris.main.TetrisGame;
import cn.com.twoke.tetris.utils.TetrisUtils;

public class TetrisManage implements KeyListener {

	private int[][] grids;
	private TetrisGame game;
	private Tetris holdTetris;
	private Random random;
	private Queue<Tetris> nexTetris;
	private int nextCount = 5;
	private int score = 0;
	private int count = 0;
	
	public TetrisManage(TetrisGame game) {
		this.grids = new int[TetrisConfig.ROW][TetrisConfig.COL];
		this.game = game;
		random = new Random();
		nexTetris = new ArrayBlockingQueue<Tetris>(nextCount);
		for (int i = 0; i < nextCount; i++) {
			nexTetris.add(randomTetris());
		}
		holdTetris = randomTetris();
		count++;
		score+=10;
	}
	
	
	private Tetris randomTetris() {
		return new Tetris(4, 0, random.nextInt(6) + 1, TetrisEnum.values()[random.nextInt(7)]);
	}
	
	private int fps = 0;
	private boolean gameOver = false;
	
	public void update() {
		if (gameOver) return;
		if (Objects.nonNull(holdTetris) && holdTetris.canMove(grids)) {
			holdTetris.update();
		} else if (Objects.nonNull(holdTetris)){
			updateToGrids(holdTetris);
			holdTetris = null;
		} else {
			if (fps > 25) {
				holdTetris = nexTetris.poll();
				if (!holdTetris.canMove(grids)) {
					gameOver = true;
					return;
				}
				nexTetris.add(randomTetris());
				fps = 0;
				count++;
				score+=10;
			}
			fps++;
		}
		updateLines();
	}

	private void updateToGrids(Tetris tetris) {
		for (int i = 0; i < tetris.getBlocks().length; i++) {
			if (tetris.getBlocks()[i][1] < TetrisConfig.ROW &&
					tetris.getBlocks()[i][0] < TetrisConfig.COL
					) {
				grids[tetris.getBlocks()[i][1]]
						[tetris.getBlocks()[i][0]] = tetris.getColor();
			}
		}
	}
	
	private void updateLines() {
		boolean flag = true;
		List<Integer> lines = new ArrayList<Integer>();
		for (int y = 0; y < grids.length; y++) {
			flag = true;
			for (int x = 0; x < grids[y].length; x++) {
				if (grids[y][x] == 0) {
					flag = false;
				}
			}
			if (flag) {
				lines.add(y);
			}
		}
		for (int i = 0; i < lines.size(); i++) {
			for (int y = lines.get(i); y > 1; y--) {
				for (int x = 0; x < grids[y].length; x++) {
					grids[y][x] = grids[y - 1][x];
				}
			}
			score+=100;
		}
	}

	public void draw(Graphics g) {
		drawPlayingTetris((Graphics2D)g);
		drawHoldTetris((Graphics2D)g);
		drawNextTetris((Graphics2D)g);
		drawGameState(g);
		drawScore(g);
	}

	private void drawScore(Graphics g) {
		g.setFont(new Font("宋体雅黑", Font.PLAIN, 15));
		g.setColor(Color.white);
		g.drawString("分数：" + score, START_X + 20, START_Y + BORDER_WIDTH + TILE_HEIGHT * 5 + 10 + 45);
		g.drawString("块数：" + count, START_X + 20, START_Y + BORDER_WIDTH + TILE_HEIGHT * 5 + 10+ 25);
	}


	private void drawGameState(Graphics g) {
		if (gameOver) {
			g.setColor(new Color(0,0,0, 200));
			g.fillRect(0, 0, 1024, 800);
			g.setColor(Color.white);
			g.setFont(new Font("宋体", Font.BOLD, 50));
			g.drawString("游戏失败！", 536 / 2 - 100, 220);
		}
	}


	private void drawPlayingTetris(Graphics2D g) {
		for (int y = 0; y < grids.length; y++) {
			for (int x = 0; x < grids[y].length; x++) {
				TetrisUtils.drawTile(g, x, y, TetrisConfig.PLAYING_PANEL_OFFSET_X, 0, grids[y][x]);
			}
		}
	}


	private void drawHoldTetris(Graphics2D g) {
		if (Objects.isNull(holdTetris)) return;
		holdTetris.draw((Graphics2D)g);
		Tetris holdTetris = new Tetris(0, 0, this.holdTetris.getColor(), this.holdTetris.getType());
		int offsetX = 0;
		for (int i = 0; i < holdTetris.getBlocks().length; i++) {
			Point p = holdTetris.calcPoint(i, 0, 1);
			if (i == 0) {
				if (holdTetris.getType() == TetrisEnum.I) {
					offsetX = (int)((21 * 6 - 21 * 4) / 2.0f);
				} else if (holdTetris.getType() == TetrisEnum.O) {
					offsetX = (int)((21 * 6 - 21 * 2) / 2.0f);
				} else if (holdTetris.getType() == TetrisEnum.Z) {
					offsetX = (int)((21 * 6 - 21 * 3f) / 2.0f);
				} else if (holdTetris.getType() == TetrisEnum.J || holdTetris.getType() == TetrisEnum.L) {
					offsetX = (int)((21 * 6 - 21 * 3f) / 2.0f);
				} else if (holdTetris.getType() == TetrisEnum.S ||
						holdTetris.getType() == TetrisEnum.T) {
					offsetX = (int)((21 * 6 - 21 * 2f) / 2.0f) + 10;
				}
				
			}
			TetrisUtils.drawTile(g, START_X + offsetX + (BORDER_WIDTH + MARGIN_WIDTH) + p.x * (TILE_WIDTH + TILE_INTERVAL),
					START_Y + (BORDER_WIDTH + MARGIN_WIDTH) + p.y * (TILE_WIDTH + TILE_INTERVAL), TILE_WIDTH, TILE_HEIGHT, 2, holdTetris.getColor());
		}
	}


	private void drawNextTetris(Graphics2D g) {
		Iterator<Tetris> iterator =  nexTetris.iterator();
		for (int j = 0; iterator.hasNext(); j++) {
			Tetris tetris = iterator.next();
			int offsetX = 0;
			for (int i = 0; i < tetris.getBlocks().length; i++) {
				Point p = tetris.calcPoint(i, 0, 1 + j * 4);
				if (i == 0) {
					if (tetris.getType() == TetrisEnum.I) {
						offsetX = (int)((21 * 6 - 21 * 4) / 2.0f);
					} else if (tetris.getType() == TetrisEnum.O) {
						offsetX = (int)((21 * 6 - 21 * 2) / 2.0f);
					} else if (tetris.getType() == TetrisEnum.Z) {
						offsetX = (int)((21 * 6 - 21 * 3f) / 2.0f);
					} else if (tetris.getType() == TetrisEnum.J || tetris.getType() == TetrisEnum.L) {
						offsetX = (int)((21 * 6 - 21 * 3f) / 2.0f);
					} else if (tetris.getType() == TetrisEnum.S ||
							tetris.getType() == TetrisEnum.T) {
						offsetX = (int)((21 * 6 - 21 * 2f) / 2.0f) + 10;
					}
					
				}
				TetrisUtils.drawTile(g, START_X + offsetX + TetrisConfig.NEXT_PANEL_OFFSET_X + (BORDER_WIDTH + MARGIN_WIDTH) + p.x * (TILE_WIDTH + TILE_INTERVAL),
						START_Y + (BORDER_WIDTH + MARGIN_WIDTH) + p.y * (TILE_WIDTH + TILE_INTERVAL), TILE_WIDTH, TILE_HEIGHT, 2, tetris.getColor());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (Objects.nonNull(holdTetris)) {
			holdTetris.keyPressed(e);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (Objects.nonNull(holdTetris)) {
			holdTetris.keyReleased(e);
		}
	}
	
	
	
	
}
