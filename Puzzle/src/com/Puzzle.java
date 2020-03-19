package com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

public class Puzzle extends JFrame implements Runnable{
	MainPanel panel;
	String path;// ͼƬ��·��
	int pattern;// ͼƬ�ĵ���
	JMenuBar jmBar;
	JMenu menu, menuSelect, menuChange, menuRank, menuHelp;
	JMenuItem itemStart, itemExit, itemView;
	JRadioButtonMenuItem pic_change[] = new JRadioButtonMenuItem[4];
	JRadioButtonMenuItem game_rank[] = new JRadioButtonMenuItem[3];
	JLabel total_time;
	JLabel total_count;
	long startTime;
	long endTime;

	public Puzzle() {
		// TODO Auto-generated constructor stub
		jmBar = new JMenuBar();
		menu = new JMenu("�˵�");
		menuSelect = new JMenu("ѡ��");
		menuHelp = new JMenu("����");
		menuChange = new JMenu("ͼƬ����");
		menuRank = new JMenu("�ȼ�");

		itemStart = new JMenuItem("��ʼ");
		itemExit = new JMenuItem("�˳�");
		itemView = new JMenuItem("�鿴����");
		
		total_time = new JLabel("ʱ�䣺");
		total_count = new JLabel("������");
		total_time.setForeground(Color.RED);
		total_count.setForeground(Color.RED);

		ButtonGroup groupChange = new ButtonGroup();
		for (int i = 0; i < pic_change.length; i++) {
			pic_change[i] = new JRadioButtonMenuItem("0" + (i + 1) + ".jpg");
			groupChange.add(pic_change[i]);
			menuChange.add(pic_change[i]);
		}
		pic_change[0].setSelected(true);

		ButtonGroup groupRank = new ButtonGroup();
		String content;
		for (int i = 0; i < game_rank.length; i++) {
			if (i == 0)
				content = "��";
			else if (i == 1)
				content = "��ͨ";
			else
				content = "����";
			game_rank[i] = new JRadioButtonMenuItem(content);
			groupRank.add(game_rank[i]);
			menuRank.add(game_rank[i]);
		}
		game_rank[0].setSelected(true);

		menu.add(itemStart);
		menu.add(itemView);
		menu.add(itemExit);
		menuSelect.add(menuChange);
		menuSelect.add(menuRank);
		jmBar.add(menu);
		jmBar.add(menuSelect);
		jmBar.add(menuHelp);
		jmBar.add(new JLabel("               "));
		jmBar.add(total_time);
		jmBar.add(new JLabel("       "));
		jmBar.add(total_count);

		this.setJMenuBar(jmBar);

		itemStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				breakState();
			}
		});

		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		itemView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton index = new JButton(new ImageIcon(path + "\\index.jpg"));
				JFrame model = new JFrame("ƴͼģ��");
				model.setSize(370, 370);
				model.setResizable(false);
				model.add(index);
				model.setVisible(true);
			}
		});

		this.setTitle("ƴͼ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(380, 420);
		this.setResizable(false);
		this.setPath();
		this.setPattern();

		panel = new MainPanel(path, pattern);

		startTime = System.currentTimeMillis();
		this.add(panel);
		this.setVisible(true);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			endTime = System.currentTimeMillis();
			int time = (int)((endTime-startTime)/1000);
			total_time.setText("ʱ��:"+time);
			total_count.setText("����:"+panel.getCount());
		}
	}
	
	public void breakState() {
		startTime = System.currentTimeMillis();
		setPattern();
		setPath();
		panel.breakRandom(path, pattern);
	}

	public void setPath() {
		// path = "img\\type1\\";
		for (int i = 0; i < pic_change.length; i++) {
			if (pic_change[i].isSelected()) {
				path = "img\\type" + (i + 1) + "\\";
			}
		}
	}

	public void setPattern() {
		// pattern = 3;
		for (int i = 0; i < game_rank.length; i++) {
			if (game_rank[i].isSelected()) {
				if (i == 0)
					pattern = 3;
				else if (i == 1)
					pattern = 4;
				else if (i == 2)
					pattern = 5;
			}

		}
	}

	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle();
		Thread th = new Thread(puzzle);
		th.start();
	}

}
