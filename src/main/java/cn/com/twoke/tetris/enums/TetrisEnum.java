package cn.com.twoke.tetris.enums;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public enum TetrisEnum {
	I(4,(index, x, y) -> new Point(x + index, y)),
	L(4,
	(index, x, y) -> {
		if (index == 0) {
			return new Point(x + 2,y);
		} else if (index == 1) {
			return new Point(x + 2,y + 1);
		} else if (index == 2) {
			return new Point(x + 1,y + 1);
		} else {
			return new Point(x,y + 1);
		}
	}),
	O(4, (index, x, y) ->  {
		if (index == 0) {
			return new Point(x,y);
		} else if (index == 1) {
			return new Point(x + 1,y);
		} else if (index == 2) {
			return new Point(x,y + 1);
		} else {
			return new Point(x + 1,y + 1);
		}
	}),
	S(4, (index, x, y) -> {
		if (index == 0) {
			return new Point(x,y);
		} else if (index == 1) {
			return new Point(x + 1,y);
		} else if (index == 2) {
			return new Point(x,y + 1);
		} else {
			return new Point(x - 1,y + 1);
		}
	}),
	Z(4, (index, x, y) -> {
		if (index == 0) {
			return new Point(x,y);
		} else if (index == 1) {
			return new Point(x + 1 ,y);
		} else if (index == 2) {
			return new Point(x + 1,y + 1);
		} else {
			return new Point(x + 2,y + 1);
		}
	}),
	T(4, (index, x, y) -> {
		if (index == 0) {
			return new Point(x,y);
		} else if (index == 1) {
			return new Point(x ,y + 1);
		} else if (index == 2) {
			return new Point(x + 1,y + 1);
		} else {
			return new Point(x - 1,y + 1);
		}
	}),
	J(4, 
			(index, x, y) -> {
				if (index == 0) {
					return new Point(x ,y);
				} else if (index == 1) {
					return new Point(x,y + 1);
				} else if (index == 2) {
					return new Point(x + 1,y + 1);
				} else {
					return new Point(x + 2,y + 1);
				}
			})
	;
	public final int size;
	public final CalcBlockHandler handler;
	
	public static Map<TetrisEnum, CalcBlockHandler[]> turnMap = new HashMap<TetrisEnum, CalcBlockHandler[]>();
	
	static {
		turnMap.put(I, new CalcBlockHandler[] {
				I.handler, (index, x, y) -> new Point(x,  y + index)
		});
		turnMap.put(O, new CalcBlockHandler[] {
				O.handler
		});
		turnMap.put(T, new CalcBlockHandler[] {
				T.handler, (index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x,y + 1);
					} else if (index == 2) {
						return new Point(x + 1,y + 1);
					} else {
						return new Point(x,y + 2);
					}
				}, (index, x, y) -> {
					if (index == 0) {
						return new Point(x,y + 1);
					} else if (index == 1) {
						return new Point(x - 1,y + 1);
					} else if (index == 2) {
						return new Point(x + 1,y + 1);
					} else {
						return new Point(x,y + 2);
					}
				}, (index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x,y + 1);
					} else if (index == 2) {
						return new Point(x - 1,y + 1);
					} else {
						return new Point(x,y + 2);
					}
				}
		});

		turnMap.put(S, new CalcBlockHandler[] {
				S.handler,
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x,y + 1);
					} else if (index == 2) {
						return new Point(x + 1,y + 1);
					} else {
						return new Point(x + 1,y + 2);
					}
				}
		});

		turnMap.put(Z, new CalcBlockHandler[] {
				Z.handler,
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x,y + 1);
					} else if (index == 2) {
						return new Point(x - 1,y + 1);
					} else {
						return new Point(x - 1,y + 2);
					}
				}
		});
//
		turnMap.put(L, new CalcBlockHandler[] {
				L.handler,
				(index, x, y) -> {
					if (index < 3) {
						return new Point(x, index + y);
					} else {
						return new Point(x + 1, y + index - 1);
					}
				},
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x + 1,y);
					} else if (index == 2) {
						return new Point(x + 2,y);
					} else {
						return new Point(x,y + 1);
					}
				},
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x + 1,y);
					} else if (index == 2) {
						return new Point(x + 1,y + 1);
					} else {
						return new Point(x + 1,y + 2);
					}
				}
		});
		
		turnMap.put(J, new CalcBlockHandler[] {
				J.handler,
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x ,y);
					} else if (index == 1) {
						return new Point(x + 1,y);
					} else if (index == 2) {
						return new Point(x ,y + 1);
					} else {
						return new Point(x,y + 2);
					}
				},(index, x, y) -> {
					if (index == 0) {
						return new Point(x ,y);
					} else if (index == 1) {
						return new Point(x + 1,y );
					} else if (index == 2) {
						return new Point(x + 2,y);
					} else {
						return new Point(x + 2,y + 1);
					}
				},
				(index, x, y) -> {
					if (index == 0) {
						return new Point(x,y);
					} else if (index == 1) {
						return new Point(x ,y + 1);
					} else if (index == 2) {
						return new Point(x,y + 2);
					} else {
						return new Point(x - 1,y + 2);
					}
				},
		});
	}
	
	private TetrisEnum(int size, CalcBlockHandler handler) {
		this.size = size;
		this.handler = handler;
	}
}
