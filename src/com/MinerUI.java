package com;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
public class MinerUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	JButton[][] buttons=new JButton[15][15];
	int[][] arr=new int[15][15];
	int boomNumber=30;
	int [][]temp={{0,1},{1,0},{0,-1},{-1,0},{-1,1},{-1,-1},{1,-1},{1,1}};
	ImageIcon iconflag = new ImageIcon("./pic/flag.jpg");
	ImageIcon iconred = new ImageIcon("./pic/red.jpg");
	ImageIcon iconwhite = new ImageIcon("./pic/white.jpg");
	
	public MinerUI() {
		
		setTitle("扫雷");
		setBounds(100,100,800,820);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		setLayout(null);
		
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				buttons[i][j]=new JButton();
				buttons[i][j].setBounds(20+i*50,20+j*50,49,49);
				this.add(buttons[i][j]);
			}
		}
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
				buttons[i][j].addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						JButton button = (JButton) e.getSource();
						if(e.getButton()== MouseEvent.BUTTON1)
						check(button);
						else if(e.getButton()== MouseEvent.BUTTON3)
						button.setIcon(iconflag);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
					}
				
				});
				
			}
		}
		
		Init(boomNumber);
		
		
		
		
		setVisible(true);
	}

	public void check(JButton button)
	{
		int x=(button.getX()-20)/50;
		int y=(button.getY()-20)/50;
		System.out.println(x+"  "+y);
		if(arr[x][y]==9)
		{
			button.setIcon(iconred);
			boom(x,y);
		}
		else if(arr[x][y]==0)
		{
			button.setFont(new Font("楷体", 0, 18));
			button.setText(arr[x][y]+"");
			arr[x][y]=10;
			for(int i=0;i<8;i++)
				if(x+temp[i][0]>=0&&x+temp[i][0]<15&&y+temp[i][1]>=0&&y+temp[i][1]<15)
				{
			    check(buttons[x+temp[i][0]][y+temp[i][1]]);
				}
		}
		else if(arr[x][y]>0&&arr[x][y]<9)
		{
			button.setFont(new Font("楷体", 0, 18));
			button.setText(arr[x][y]+"");
		}
		else
		{
			
		}
	}
	
	public void Init(int number)
	{
		while(number!=0)
		{
			int x=(int)(Math.random()*14.9);
			int y=(int)(Math.random()*14.9);
			if(arr[x][y]!=9)
			{
				arr[x][y]=9;
				number=number-1;
			}
		}
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
			   if(arr[i][j]!=9)
			   arr[i][j]=search(i,j);
			}
		}
	}
	
	public void boom(int x,int y)
	{
		for(int i=0;i<15;i++)
		{
			for(int j=0;j<15;j++)
			{
			   if(x==i&&y==j)
				   continue;
			   if(arr[i][j]==9)
			   {
				   buttons[i][j].setIcon(iconwhite);
			   }
			}
		}
	}
	
	public int search(int x,int y)
	{
		int res=0;
		for(int i=0;i<8;i++)
		   if(x+temp[i][0]>=0&&x+temp[i][0]<15&&y+temp[i][1]>=0&&y+temp[i][1]<15&&arr[x+temp[i][0]][y+temp[i][1]]==9)
			   res++;
		return res;
	}
	
}
