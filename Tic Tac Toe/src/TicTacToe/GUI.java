package TicTacToe;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EtchedBorder;



public class GUI extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	JPanel grid=new JPanel();
	JButton[] board= new JButton[9];
	JFrame frame =new JFrame();
	JRadioButton c=new JRadioButton("computer");
	JRadioButton h=new JRadioButton("human");
	ButtonGroup group=new ButtonGroup();
	JButton resetBoard=new JButton();
	JButton resetScore=new JButton("Reset Score");
	JPanel ScoreX=new JPanel();
	JPanel ScoreO=new JPanel();
	JLabel ScoreXlabel=new JLabel();
	JLabel ScoreOlabel=new JLabel();
	JCheckBox first=new JCheckBox("Play First");
	JLabel tic=new JLabel("tic");
	JLabel tac=new JLabel("tac");
	JLabel toe=new JLabel("toe");
	JLabel message=new JLabel();
	JPanel messenger=new JPanel();
	LineDrawer draw=new LineDrawer();
	JLayeredPane layer=new JLayeredPane();
	
	
	
	private boolean playFirst=true; 
	private boolean turn=false;// false x  true o
	private boolean vsComputer=true;
	private boolean gameOn=false;
	private char AIsymbol='O';
	private char PlayerSymbol='X';
	private int xcounter=0;
	private int ocounter=0;
	
	
	
	public int getXcounter() {
		return xcounter;
	}
	public void setXcounter(int xcounter) {
		this.xcounter = xcounter;
	}
	public int getOcounter() {
		return ocounter;
	}
	public void setOcounter(int ocounter) {
		this.ocounter = ocounter;
	}
	public char getPlayerSymbol() {
		return PlayerSymbol;
	}
	public void setPlayerSymbol(char playerSymbol) {
		PlayerSymbol = playerSymbol;
	}
	public char getAIsymbol() {
		return AIsymbol;
	}
	public void setAIsymbol(char aIsymbol) {
		AIsymbol = aIsymbol;
	}
	public boolean isPlayFirst() {
		return playFirst;
	}
	public void setPlayFirst(boolean playFirst) {
		this.playFirst = playFirst;
	}
	public boolean isVsComputer() {
		return vsComputer;
	}
	public void setVsComputer(boolean vsComputer) {
		this.vsComputer = vsComputer;
	}
	public boolean isGameOn() {
		return gameOn;
	}
	public void setGameOn(boolean gameOn) {
		this.gameOn = gameOn;
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	Color xColor=new Color(0,255,0);
	Color oColor=new Color(255,167,14);
	Color boardBackground=new Color(40,40,40);
	Color framebackground=new Color(80,80,80);
	Color ResetColor=new Color(120,120,120);
	int difficulty=0; // 0 easy 1 medium 2.hard 3.impossible
	String[] gameMode= {"Easy","Medium","Hard","Impossible"};
	JComboBox<String> comboBox=new JComboBox<String>(gameMode);
	char[] boardArray= {' ',' ',' ',' ',' ',' ',' ',' ',' '};
	
	
	GUI(){
		
		//Create mainframe
		frame.setSize(328,500);
		frame.setTitle("TicTacToe");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(framebackground);//change background color;
		frame.setLayout(null);
		Image frameIcon=new ImageIcon(GUI.class.getResource("/res/tictactoeIcon.png")).getImage();
		frame.setIconImage(frameIcon);
		
		// Create Layer 
		layer.setBounds(0,0,328,500);
		frame.add(layer);
		
		
		//Create Messenger
		messenger.setBounds(7,100,300,45);
		messenger.setBackground(Color.DARK_GRAY);
		//frame.add(messenger);
		messenger.add(message);
		message.setFont(new Font("Lato",Font.PLAIN,30));
		messageHandler(boardArray, turn,4);
		
		
		//Create Reset Button
		resetBoard.setBounds(250,40,50,50);
		resetBoard.setVisible(true);
		resetBoard.setEnabled(false);
		resetBoard.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.GRAY,Color.DARK_GRAY));
		resetBoard.setBackground(ResetColor);
		ImageIcon resetIcon=new ImageIcon(new javax.swing.ImageIcon(GUI.class.getResource("/res/resetIcon.png")).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		resetBoard.setIcon(resetIcon);
		resetBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				c.setEnabled(true);
				h.setEnabled(true);
				first.setEnabled(true);
				comboBox.setEnabled(true);
				resetScore.setEnabled(true);
				messageHandler(boardArray, turn, 4);
				lineMaker(false,0,0,0,0,Color.BLACK);
				for(int i=0;i<9;i++) {
					board[i].setEnabled(true);
					board[i].setText("");
					boardArray[i]=' ';
				}
			}
		});
		//frame.add(resetBoard);
		
		
		//Create ComboBox
		comboBox.setBounds(10,10,100,20);
		comboBox.setSelectedIndex(0);
		comboBox.setBackground(Color.GRAY);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent combo) {
				difficulty=comboBox.getSelectedIndex();
			}
		});
		
		
		//create Game Mode
		c.setBounds(10,30,100,20);
		h.setBounds(10,50,100,20);
		c.setFocusable(false);
		h.setFocusable(false);
		c.setBackground(framebackground);
		h.setBackground(framebackground);
		c.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c) {
				setVsComputer(true);
				comboBox.setEnabled(true);
			}
		});
		h.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent h) {
				setVsComputer(false);
				comboBox.setEnabled(false);
			}
		});
		group.add(c);
		group.add(h);
		//frame.add(c);
		//frame.add(h);
		//frame.add(comboBox);
		c.setSelected(true);
		
		//Create Score Board
		ScoreX.setBounds(120,40,50,50);
		ScoreO.setBounds(180,40,50,50);
		ScoreX.setBackground(boardBackground);
		ScoreO.setBackground(boardBackground);
		ScoreX.add(ScoreXlabel);
		ScoreO.add(ScoreOlabel);
		ScoreXlabel.setFont(new Font("Lote",Font.PLAIN,40));
		ScoreOlabel.setFont(new Font("Lote",Font.PLAIN,40));
		ScoreXlabel.setForeground(xColor);
		ScoreOlabel.setForeground(oColor);
		ScoreHandler(0,0);
		resetScore.setBounds(120,10,110,20);
		resetScore.setBackground(new Color(120,120,120));
		resetScore.setFocusable(false);
		resetScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				setXcounter(0);
				setOcounter(0);
				ScoreHandler(0,0);
			}
		});
		//frame.add(resetScore);
		//frame.add(ScoreX);
		//frame.add(ScoreO);
		
		
		
		//Create play first
		first.setBounds(10,70,80,20);
		first.setBackground(framebackground);
		first.setFocusable(false);
		first.setSelected(true);
		first.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent l) {
				setPlayFirst(first.isSelected());
				System.out.println(playFirst);
				if(first.isSelected()) {
					setTurn(false);
				}else {			
					setTurn(true);
				}
				System.out.println("turn changed to"+turn);
			}
		});
		//frame.add(first);
		
		//Create Board
		grid.setLayout(new GridLayout(3,3));
		grid.setBackground(Color.WHITE);
		grid.setBounds(7,150,300,300);
		//frame.add(grid);
		for(int i=0;i<9;i++) {
			board[i]=new JButton();
			grid.add(board[i]);
			board[i].addActionListener(this);
			board[i].setFocusable(false);
			board[i].setBackground(boardBackground);
		}
		int x=15;
		// create draw panel
		draw.setBounds(7+x,150+x,300-2*x,300-2*x);
		lineMaker(false,0,0,0,0,Color.BLACK);
		
		//add everything to layer
		layer.add(messenger,Integer.valueOf(0));
		layer.add(comboBox,Integer.valueOf(0));
		layer.add(c,Integer.valueOf(0));
		layer.add(h,Integer.valueOf(0));
		layer.add(grid,Integer.valueOf(0));
		layer.add(ScoreO,Integer.valueOf(0));
		layer.add(ScoreX,Integer.valueOf(0));
		layer.add(draw,Integer.valueOf(1));
		layer.add(resetBoard,Integer.valueOf(0));
		layer.add(resetScore,Integer.valueOf(0));
		layer.add(first,Integer.valueOf(0));
		
		
		
		frame.setVisible(true);
		
	}
	 
	 
	//lock down options when game starts
	void gameStarter() {
		c.setEnabled(false);
		h.setEnabled(false);
		comboBox.setEnabled(false);
		resetBoard.setEnabled(false);
		first.setEnabled(false);
		resetScore.setEnabled(false);
		message.setText("");
		
		
	}
	
	
	
	//when game is ended 
	void gamefinisher(char[] a) {
		resetBoard.setEnabled(true);
		messageHandler(a,turn,2);
		setGameOn(false);
		if(playFirst==true) {
			setTurn(false);
		}else {
			setTurn(true);
		}
		char winner=win(a);
		if(winner!=' ') {
			winline(a);
			if(winner=='X') {
				setXcounter(getXcounter()+1);
			}else {
				setOcounter(getOcounter()+1);
			}
			ScoreHandler(getXcounter(),getOcounter());
		}else {
			messageHandler(a,turn,3);
		}
	}
	
	
	void winline(char[] a) {
		int x=(int) draw.getSize().getWidth()/2;
		int y=(int) draw.getSize().getHeight()/2;
		int x1=0;
		int y1=0;
		int x2=0;
		int y2=0;
		int v=100;
		Color c;
		for(int i=0;i<3;i++) {
			if(a[i]!=' ' && a[i]==a[i+3] && a[i]==a[i+6]) {
				x1=(i-1)*v+x;
				x2=x1;
				y1=0;
				y2=2*y;
			}else if(a[3*i]!=' ' && a[3*i]==a[3*i+1] && a[3*i]==a[3*i+2]) {
				x1=0;
				x2=2*x;
				y1=(i-1)*v+y;
				y2=y1;
			}
		}
		if(a[0]==a[4] && a[0]==a[8] && a[0]!=' ') {
			x1=0;
			y1=0;
			x2=2*x;
			y2=2*y;
		}
		if(a[2]==a[4] && a[4]==a[6] && a[2]!=' ') {
			x1=2*x;
			y1=0;
			x2=0;
			y2=2*y;
		}
		if(win(a)=='X') {
			c=xColor;
		}else {
			c=oColor;
		}
		lineMaker(true,x1,y1,x2,y2,c);
	}
	
	void lineMaker(boolean isDraw,int x1,int y1,int x2,int y2,Color c) {
		draw.setDraw(isDraw);
		draw.setX1(x1);
		draw.setY1(y1);
		draw.setX2(x2);
		draw.setY2(y2);
		draw.setC(c);
		draw.repaint();
	}
	
	
	boolean endControl(char[] a) {
		for(int i=0;i<9;i++) {
			if(a[i]==' ') {
				return false;
			}
		}
		return true;
	}
	
	
	
	
	void AI(int difficulty,boolean turn ,char[] a,char AIsymbol,char PlayerSymbol) {
		try
		{
		    Thread.sleep(50);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		if(difficulty==0) {
			easyAI(turn,a);
		}else if(difficulty==1) {
			normalAI(a,turn,AIsymbol,PlayerSymbol);
		}else if(difficulty==2) {
			hardAI(a,turn,AIsymbol,PlayerSymbol);
		}else if(difficulty==3) {
			impossibleAI(a,turn,AIsymbol,PlayerSymbol);
		}
	}
	
	
	
	void easyAI(boolean turn,char[] a) {
		Random random=new Random();
		boolean moved=false;
		if(win(a)==' ' && endControl(a)==false) {
			while(moved==false) {
				int i=random.nextInt(9);
				if(a[i]==' ') {
					if(turn==false) {
						a[i]='X';
					}else {
						a[i]='O';
					}
					BoardChanger(a,i);
					setTurn(!turn);
					moved=true;
					if(win(a)!=' ' || endControl(a)==true) {
						gamefinisher(a);
					}
					
				}
			}
		}
		
	}
	
	void normalAI(char[] a,boolean turn,char AIsymbol,char PlayerSymbol) {
		Random random=new Random();
		int chance=random.nextInt(100);
		int barrier=50;
		if(chance>barrier) {
			easyAI(turn,a);
		}else {
			impossibleAI(a,turn,AIsymbol,PlayerSymbol);
		}
	}
	
	void hardAI(char[] a,boolean turn,char AIsymbol,char PlayerSymbol) {
		Random random=new Random();
		int chance=random.nextInt(100);
		int barrier=75;
		if(chance>barrier) {
			easyAI(turn,a);
		}else {
			impossibleAI(a,turn,AIsymbol,PlayerSymbol);
		}
	}
	
	void impossibleAI(char[] a,boolean turn,char AIsymbol,char PlayerSymbol) {
		int move=-1;
		int score=-2;
		int i;
		System.out.println("turn "+turn);
		for(i=0;i<9;i++) {
			if(a[i]==' ') {
				a[i]=AIsymbol;
				int tmpscore=-minimax(a,!turn,AIsymbol,PlayerSymbol);
				a[i]=' ';
				if(tmpscore>score) {
					score=tmpscore;
					move=i;
				}
			}
		}
		if(move==-1) {
			return;
		}
		a[move]=AIsymbol;
		BoardChanger(a,move);
		setTurn(!turn);
		System.out.println("turn "+turn);
		if(win(a)!=' ') {
			gamefinisher(a);
		}
	}
	
	
	char win (char[] a) {
		for(int i=0;i<3;i++) {
			if(a[i]!=' ' && a[i]==a[i+3] && a[i]==a[i+6]) {
				return a[i];
			}else if(a[3*i]!=' ' && a[3*i]==a[3*i+1] && a[3*i]==a[3*i+2]) {
				return a[3*i];
			}
		}
		if(a[0]==a[4] && a[0]==a[8] && a[0]!=' ') {
			return a[0];
		}
		if(a[2]==a[4] && a[4]==a[6] && a[2]!=' ') {
			return a[2];
		}
		return ' ';
	}
	
	void BoardChanger(char[] a,int i) {
		board[i].setText(String.valueOf(a[i]));
		board[i].setFont(new Font("Lato",Font.BOLD,70));
		if(a[i]=='X') {
			board[i].setForeground(xColor);
		}else {
			board[i].setForeground(oColor);
		}
	}
	
	
	//option 1 win option 2 turn option 3 draw
	void messageHandler(char[] a,boolean turn,int option) {
		if(option==1) {
			if(turn==false) {
				message.setText("X turn");
			}else {
				message.setText("Y turn");
			}
		}else if(option==2) {
			char winner=win(a);
			if(winner!=' ') {
				if(winner=='X') {
					message.setText("X Wins");
					message.setForeground(xColor);
				}else {
					message.setText("O Wins");
					message.setForeground(oColor);
				}
			}
		}else if(option==3) {
			message.setText("Draw");
			message.setForeground(Color.BLACK);
		}else if(option==4) {
			message.setText("Click on board to start");
			message.setForeground(Color.BLACK);
		}
	}
	void ScoreHandler(int x,int o) {
			ScoreXlabel.setText(String.valueOf(x));
			ScoreOlabel.setText(String.valueOf(o));
	}
	
	int minimax(char[] a,boolean turn,char AIsymbol,char PlayerSymbol) {
		char winner=win(a);
		int player=1;
		int ai=1;
		char movesymbol=AIsymbol;
		
	
		//if player turn  //false player true AI
		
		if(turn==false) {
			player=-1;
			movesymbol=PlayerSymbol;
		}
		
		//if game ended
		if(winner!=' ') {
			if(winner!=AIsymbol) {
				ai=-1;
			}
			return(player*ai);
		}
		
		int move=-1;
		int score=-2;
		for(int i=0;i<9;i++) {
			if(a[i]==' ') {
				a[i]=movesymbol;
				int tscore=-minimax(a,!turn,AIsymbol,PlayerSymbol);
				if(tscore>score) {
					score=tscore;
					move=i;
				}
				a[i]=' ';

			}
		}
		if(move==-1) {
			return 0;
		}
		return score;
	}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<9;i++) {
			if(e.getSource()==board[i] && boardArray[i]==' ' && win(boardArray)==' ') {
				if(isGameOn()==false) {
					gameStarter();
					setGameOn(true);
					if(playFirst==false && vsComputer==true) {
						AI(difficulty,turn,boardArray,AIsymbol,PlayerSymbol);
						return;
					}
					
				}

		
	
				if(turn==false) {
					boardArray[i]='X';
				}else {
					boardArray[i]='O';
				}	
				BoardChanger(boardArray,i);
				setTurn(!turn);
				if(vsComputer==true) {	
					if(endControl(boardArray)==true || win(boardArray)!=' ') {
						gamefinisher(boardArray);
					}else {
						AI(difficulty,turn,boardArray,AIsymbol,PlayerSymbol);
					}
					
				}else {
					if(win(boardArray)!=' ' || endControl(boardArray)==true) {
						gamefinisher(boardArray);
					}
				}
					
					
				
				
					
					
				
			}
		}
		
	}
	

}
