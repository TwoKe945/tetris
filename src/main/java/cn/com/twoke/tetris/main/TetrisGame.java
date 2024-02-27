package cn.com.twoke.tetris.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import cn.com.twoke.tetris.framework.core.Game;
import cn.com.twoke.tetris.framework.core.GamePanel;
import cn.com.twoke.tetris.manage.TetrisManage;
import cn.com.twoke.tetris.utils.TetrisUtils;

import static cn.com.twoke.tetris.constant.TetrisConfig.*;

public class TetrisGame extends Game implements KeyListener {
	private TetrisManage tetrisManage;
	public TetrisGame() {
		super(TetrisGame::buildGamePanel);
		this.panel.addKeyListener(this);
	}
	
	public static GamePanel buildGamePanel(Game game) {
		return new GamePanel(game, 1024, 800);
	}
	
	@Override
	protected void initialzer() {
		tetrisManage = new TetrisManage(this);
	}

	@Override
	protected void doDraw(Graphics g) {
		TetrisUtils.drawContainer((Graphics2D)g, START_X, START_Y, COL, ROW);
		TetrisUtils.drawContainer((Graphics2D)g, START_X + NEXT_PANEL_OFFSET_X , START_Y, 6, 20);
		tetrisManage.draw(g);
	}

	@Override
	protected void update() {
		tetrisManage.update();
	}

	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		tetrisManage.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		tetrisManage.keyReleased(e);
	}

}
