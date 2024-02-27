package cn.com.twoke.tetris.constant;

import java.awt.Color;

public interface TetrisColor {

	
	Color BLUE = new Color(0x93E2F9); 
	Color BLUE_LITE = new Color(0xC1F7FC); 
	Color BLUENESS = new Color(0x01F0F1);
	Color BLUENESS_LITE = new Color(0xA0FFFF);
	Color YELLOW = new Color(0xFCEA68);
	Color YELLOW_LITE = new Color(0xFBFBEB);
	Color PURPLE = new Color(0xA000F1);
	Color PURPLE_LITE = new Color(0xEDABFF);
	Color GREEN = new Color(0x80DFAC);
	Color GREEN_LITE = new Color(0xA4FBCE);
	Color ORANGE = new Color(0xEFC26D);
	Color ORANGE_LITE = new Color(0xFFEB99);
	Color RED = new Color(0xFF6A9C);
	Color RED_LITE = new Color(0xFDABCB);
	
	
	Color[][] COLORS = new Color[][] {
		{ Color.GRAY,  new Color(0,0,0,25)},
		{ TetrisColor.BLUE_LITE, TetrisColor.BLUE  },
		{ TetrisColor.YELLOW_LITE, TetrisColor.YELLOW  },
		{ TetrisColor.PURPLE_LITE, TetrisColor.PURPLE  },
		{ TetrisColor.GREEN_LITE, TetrisColor.GREEN  },
		{ TetrisColor.ORANGE_LITE, TetrisColor.ORANGE  },
		{ TetrisColor.RED_LITE, TetrisColor.RED  },
		{ TetrisColor.BLUENESS_LITE, TetrisColor.BLUENESS  }
	};
	
	int BLACK_VALUE = 0;
	int BLUE_VALUE = 1;
	int YELLOW_VALUE = 2;
	int PURPLE_VALUE = 3;
	int GREEN_VALUE = 4;
	int ORANGE_VALUE = 5;
	int RED_VALUE = 6;
	int BLUENESS_VALUE = 7;
}
