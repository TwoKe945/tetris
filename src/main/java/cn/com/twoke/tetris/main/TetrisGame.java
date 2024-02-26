package cn.com.twoke.tetris.main;

import java.awt.Graphics;

import cn.com.twoke.tetris.framework.core.Game;
import cn.com.twoke.tetris.framework.core.GamePanel;

public class TetrisGame extends Game {

	public TetrisGame() {
		super(TetrisGame::buildGamePanel);
	}
	
	public static GamePanel buildGamePanel(Game game) {
		return new GamePanel(game, 1024, 800);
	}

	@Override
	protected void doDraw(Graphics g) {
	}

}
