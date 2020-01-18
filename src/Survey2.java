/* 화면 리소스 필요 
 * 입력화면 : ex)SSAT 검사
 * 검사화면 : 질문 내용, 질문 유형(사회성, 봉사성 등)
 * 출력화면 : 막대그래프, 분석표
 * 조사대상 : 충남대 공과대학 학생
 *  */

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;

public class Survey2 {
	// 척도 스태틱 변수
	int scale[] = new int[5];
	Survey2() {
			savedCompany = "건축학과";
			
		//기본 점수 70점
		scale[0] = 70;
		scale[1] = 70;
		scale[2] = 70;
		scale[3] = 70;
		scale[4] = 70;
	}
	
	Frame1 frame1 = new Frame1();
	//Frame2 frame2 = new Frame2();
	//Frame3 frame3 = new Frame3();
	//Frame4 frame4 = new Frame4(); 밑에것도 없애야함
	//Frame5 frame5 = new Frame5();

	String savedCompany;
	String savedName;
	String savedNumber;
	
	
	/*--------------------------------------------------------------------------------------------- 프레임 1 ------------------------------------------------------------------------------*/	
	class Frame1 extends JFrame {
		Container contentPane;
		Frame2 frame2 = new Frame2();
		
		JLabel name;
		JTextField namebox; 
		JLabel party;
		JComboBox partybox;
		JLabel number;
		JTextField numberbox;
		JButton nextbtn;
		JPanel south;
		String[] depart = {"건축학과", "건축공학과", "토목공학과", "환경공학과", "기계공학과", "기계설계공학과", "메카트로닉스공학과", "항공우주공학과", "선박해양공학과", "나노소재공학과", "재료공학과", "고분자공학과", "유기소재섬유시스템공학과", "정밀응용화학과", "화학공학과", "전기공학과", "전자공학과", "전파공학과", "정보통신공학과", "컴퓨터공학과"};
		JComboBox cb;
		int index;
		
		Frame1() {
			setTitle("충남대 공과대학 학생대상 인성검사"); // frame1의 타이틀
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			contentPane = getContentPane(); // 컨탠트팬 알아내기
			contentPane.setLayout(new BorderLayout());
			
			name = new JLabel("이름 : ");
			namebox = new JTextField(10);
			party = new JLabel("학과 : ");
			partybox = new JComboBox(depart);
			number = new JLabel("학번 (4자리 이상~10자리 미만) : ");
			numberbox = new JTextField(10);
			nextbtn = new JButton("ENTER");
			south = new JPanel();
			
			ImageIcon main = new ImageIcon("images/image0.jpg");
			JLabel imageLabel = new JLabel(main);
			
			this.add(imageLabel, BorderLayout.CENTER);
			south.add(party);
			south.add(partybox);
			south.add(number);
			south.add(numberbox);
			south.add(name);
			south.add(namebox);
			south.add(nextbtn);
			this.add(south, BorderLayout.SOUTH);
			partybox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cb = (JComboBox)e.getSource();
					index = cb.getSelectedIndex();
					savedCompany = depart[index];
				}
			});
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					
					savedName = namebox.getText(); //이름과 학번 저장
					savedNumber = numberbox.getText();
					System.out.println("저장된 학과 : " + savedCompany);
					System.out.println("저장된 학번 : " + savedNumber);
					System.out.println("저장된 이름 : " + savedName);
					
					if(savedNumber.equals(null)|| savedNumber.equals("")) {
						JOptionPane.showMessageDialog(null,"학번을 입력하세요","ERROR",JOptionPane.ERROR_MESSAGE);
						numberbox.setText("");
						namebox.setText("");
						repaint();
					}
					else if(savedNumber.length() < 4) {
						JOptionPane.showMessageDialog(null,"학번을 4자리 이상 입력하세요","ERROR",JOptionPane.ERROR_MESSAGE);
						numberbox.setText("");
						namebox.setText("");	
						repaint();
					}
					else if(savedNumber.length() > 9) {
						JOptionPane.showMessageDialog(null,"학번을 10자리 미만으로 입력하세요","ERROR",JOptionPane.ERROR_MESSAGE);
						numberbox.setText("");
						namebox.setText("");	
						repaint();
					}
					else if(savedName.equals(null)|| savedName.equals("")) {
						JOptionPane.showMessageDialog(null,"이름을 입력하세요","ERROR",JOptionPane.ERROR_MESSAGE);
						numberbox.setText("");
						namebox.setText("");
						repaint();
					}
					else {
			
						try{
							int temp = Integer.parseInt(savedNumber);
							
							//frame2 = new Frame2();
							frame1.setVisible(false);
							frame2.setVisible(true);
							numberbox.setText("");
							namebox.setText("");
							repaint();
						}
						catch(NumberFormatException ee){
							JOptionPane.showMessageDialog(null,"학번을 숫자로만 입력하세요","ERROR",JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			});
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				     close();
				    }
			});
			setSize(1024, 768);
			setVisible(true);
			setLocation(50,50);
		}
		public void close(){
			if(JOptionPane.showConfirmDialog(null,"정말로 종료하시겠습니까?","인성검사",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
				return;
			}
			System.exit(0);
		}
	} // 프레임에서 프레임 2로 넘어가면 학번, 이름 저장
	
	/*--------------------------------------------------------------------------------------------- 프레임 2 ------------------------------------------------------------------------------*/
	class Frame2 extends JFrame {
		Container contentPane;
		int i=0, j=0;
		Frame3 frame3 = new Frame3();
		//라디오버튼 생성
		JRadioButton rbtn[][] = new JRadioButton[10][5];
		Frame2() {
			setTitle("충남대 공과대학 학생대상 인성검사");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			contentPane = getContentPane(); // 컨탠트팬 알아내기
			
			//contentPane.setLayout(new FlowLayout()); // ☆그리드 레이아웃 제대로 설정이 안됨
			contentPane.setLayout(null);
			
			JLabel title = new JLabel(" 문제＼답변"); // 문제＼답변
			title.setBounds(0, 0, 630,63); // 위치와 사이즈 지정
			title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			JLabel answers[] = new JLabel[5];
			String[] answer = {"매우 그렇다", "그렇다", "보통이다", "아니다", "매우 아니다"};
			// 기본 답변 부착
			for(i=0;i<5;i++){
				answers[i] = new JLabel(answer[i], SwingConstants.CENTER); // 중앙정렬
				answers[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				answers[i].setLocation(630+(75*i),0);
				answers[i].setSize(75,63);
				this.add(answers[i]);
			}
			
			//기본 질문 부착
			JLabel questions[] = new JLabel[10];
			String[] question = {" 1. 공부를 열심히 안 해도 부정적인 방법으로 높은 점수를 받을수 있다."," 2. 규칙을 어기는 사람을 보면 나서서 지적한다."," 3. 집단의 구성원으로서 동료들과 함께 일하는 것을 매우 좋아한다",
					" 4. 남의 물건을 슬쩍 집어오더라도 크게 신경쓰지 않는다."," 5. 윗사람에게 아부하는 것은 나쁜 일이 아니다.", " 6. 내가 원하는 것보다 팀이 바라는 대로 일을 진행한다."," 7. 상황에 따라 내 생각을 말할 때도 있고, 그렇지 않을때도 있다.",
					" 8. 엄격한 규칙과 규제가 존재해야 질서있는 사회가 구현된다."," 9. 원하는것은 꼭 이뤄야 직성이 풀린다.", " 10. 언제나 도덕적인것만은 아니다."};
			
			for(i=0;i<10;i++) {
				questions[i] = new JLabel(question[i]);
				// 폰트 설정 questions[i].setFont(new Font("Arial", Font.PLAIN, 10));
				questions[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				questions[i].setBounds(0,63+(63*i),630, 63);
				this.add(questions[i]);
			}
			
			
			//라디오 버튼 그룹 객체 생성
			ButtonGroup[] g = new ButtonGroup[10];
			
			//라디오패널 생성
			JPanel radioPanel[] = new JPanel[10];
			//라디오 그룹에 버튼 묶기
			for(i=0;i<10;i++) {
				g[i] = new ButtonGroup();
				radioPanel[i] = new JPanel(); 
				radioPanel[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				radioPanel[i].setBounds(630,63+(63*i),375,63);
				radioPanel[i].setLayout(new GridLayout(0, 5));
				//radioPanel[i].setBackground(Color.blue);
				for(j=0;j<5;j++) {
					rbtn[i][j] = new JRadioButton();
					g[i].add(rbtn[i][j]); //그룹묶기
					
					radioPanel[i].add(rbtn[i][j]);
					// Will rbtn 부착
					
					// Will 리스너 부착해야함 
				}
				this.add(radioPanel[i]);
			}
			
			JButton backbtn = new JButton("BACK");
			backbtn.setBounds((1024/2 - 95), 695, 80, 30);
			JButton nextbtn = new JButton("ENTER");
			nextbtn.setBounds((1024/2 + 15), 695, 80, 30);	
			this.add(title);
			this.add(backbtn);
			this.add(nextbtn);
			
			backbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					// 처음으로 돌아가기 yes or no
					if(JOptionPane.showConfirmDialog(null, "검사를 포기하고 처음으로 돌아가시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						frame1.frame2.setVisible(false);
						frame1.setVisible(true);
						repaint();
					}
				}
			});
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					int k = 0;
					for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
						for(j=0;j<5;j++){
							if(rbtn[i][j].isSelected()){
								k++; // k = 선택된 라디오버튼 갯수
							}
						}
					}
					if(k==10){
						//frame3 = new Frame3();
						frame1.frame2.setVisible(false);
						frame3.setVisible(true);
						repaint();
					}
					else{
						JOptionPane.showMessageDialog(null, "항목에 모두 체크하세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				     close();
				    }
			});
			setSize(1024, 768);
			setVisible(false);
			setLocation(50,50);
		}
		
		/* 점수 계산 방법
		// 라디오버튼 아이템 이벤트
		class MyItemListener implements ItemListener{
			public void itemStateChanged(ItemEvent e){
				if(e.getStateChange() == ItemEvent.DESELECTED) { // 아이템이 해제 되었을 때
					
				}
				else if(e.getStateChange() == ItemEvent.SELECTED) { // 아이템이 선택 되었을 때
					
				}
			}
		}*/
		
		public void close(){
			if(JOptionPane.showConfirmDialog(null,"정말로 종료하시겠습니까?","인성검사",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
				return;
			}
			System.exit(0);
		}
		public void rbtnSum() { // 점수 계산
			for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
				for(j=0;j<5;j++){
					if(rbtn[i][j].isSelected()){
						if(i==0 || i ==5){ //scale[0]에 점수 ++
							// 몇점? 5-j점. 최고점5~최저점1
							scale[0] += (5-j);
						}
						else if(i==1 || i ==6){ //scale[1]에 점수 ++
							scale[1] += (5-j);
						}
						else if(i==2 || i ==7){ //scale[2]에 점수 ++
							scale[2] += (5-j);
						}
						else if(i==3 || i ==8){ //scale[3]에 점수 ++
							scale[3] += (5-j);
						}
						else if(i==4 || i ==9){ //scale[4]에 점수 ++
							scale[4] += (5-j);
						}
					}
				}
			}
		}
	}
	
	/*--------------------------------------------------------------------------------------------- 프레임 3 ------------------------------------------------------------------------------*/
	class Frame3 extends JFrame {
		Container contentPane;
		int i=0, j=0;
		Frame4 frame4 = new Frame4();
		//라디오버튼 생성
		JRadioButton rbtn[][] = new JRadioButton[10][5];
		Frame3() {
			setTitle("충남대 공과대학 학생대상 인성검사");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			contentPane = getContentPane(); // 컨탠트팬 알아내기
			
			//contentPane.setLayout(new FlowLayout()); // ☆그리드 레이아웃 제대로 설정이 안됨
			contentPane.setLayout(null);
			
			JLabel title = new JLabel(" 문제＼답변"); // 문제＼답변
			title.setBounds(0, 0, 630,63); // 위치와 사이즈 지정
			title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			JLabel answers[] = new JLabel[5];
			String[] answer = {"매우 그렇다", "그렇다", "보통이다", "아니다", "매우 아니다"};
			// 기본 답변 부착
			for(i=0;i<5;i++){
				answers[i] = new JLabel(answer[i], SwingConstants.CENTER); // 중앙정렬
				answers[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				answers[i].setLocation(630+(75*i),0);
				answers[i].setSize(75,63);
				this.add(answers[i]);
			}
			
			//기본 질문 부착
			JLabel questions[] = new JLabel[10];
			String[] question = {" 11. 성적이나 성과가 중간 수준은 되어야 만족한다."," 12. 다른 사람보다 더 협조적이지도 덜 협조적이지도 않다"," 13. 상대를 이용하기 위해 마음에 없는 말을 한다",
					" 14. 남들보다 뒤쳐지지만 않으면 괜찮다."," 15. 다른 사람들 만큼의 도덕적 기준을 가지고 있다.", " 16. 성공을 위해 노력하고 싶은 마음이 없다."," 17. 다른 사람들과 협조적이 되려고 노력하지만, 뜻대로 되지 않는 경우도 있다.",
					" 18. 범죄를 저지르면 처벌을 받는 것이 마땅하다."," 19. 성취하기 어려운 목표를 세우지 않는다.", " 20. 의도적으로 거짓말을 할 때가 많다."};
			
			for(i=0;i<10;i++) {
				questions[i] = new JLabel(question[i]);
				// 폰트 설정 questions[i].setFont(new Font("Arial", Font.PLAIN, 10));
				questions[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				questions[i].setBounds(0,63+(63*i),630, 63);
				this.add(questions[i]);
			}
			
			
			//라디오 버튼 그룹 객체 생성
			ButtonGroup[] g = new ButtonGroup[10];
			
			//라디오패널 생성
			JPanel radioPanel[] = new JPanel[10];
			//라디오 그룹에 버튼 묶기
			for(i=0;i<10;i++) {
				g[i] = new ButtonGroup();
				radioPanel[i] = new JPanel(); 
				radioPanel[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				radioPanel[i].setBounds(630,63+(63*i),375,63);
				radioPanel[i].setLayout(new GridLayout(0, 5));
				//radioPanel[i].setBackground(Color.blue);
				for(j=0;j<5;j++) {
					rbtn[i][j] = new JRadioButton();
					g[i].add(rbtn[i][j]); //그룹묶기
					
					radioPanel[i].add(rbtn[i][j]);
					// Will rbtn 부착
					
					// Will 리스너 부착해야함 
				}
				this.add(radioPanel[i]);
			}
			
			JButton backbtn = new JButton("BACK");
			backbtn.setBounds((1024/2 - 95), 695, 80, 30);
			JButton nextbtn = new JButton("ENTER");
			nextbtn.setBounds((1024/2 + 15), 695, 80, 30);	
			this.add(title);
			this.add(backbtn);
			this.add(nextbtn);
			
			backbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					
						frame1.frame2.frame3.setVisible(false);
						frame1.frame2.setVisible(true);
						repaint();
				}
			});
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					int k = 0;
					for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
						for(j=0;j<5;j++){
							if(rbtn[i][j].isSelected()){
								k++; // k = 선택된 라디오버튼 갯수
							}
						}
					}
					if(k==10){
						//frame4 = new Frame4();
						frame1.frame2.frame3.setVisible(false);
						frame4.setVisible(true);
						repaint();
					}
					else{
						JOptionPane.showMessageDialog(null, "항목에 모두 체크하세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				     close();
				    }
			});
			setSize(1024, 768);
			setVisible(false);
			setLocation(50,50);
		}
		
		public void close(){
			if(JOptionPane.showConfirmDialog(null,"정말로 종료하시겠습니까?","인성검사",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
				return;
			}
			System.exit(0);
		}
		
		public void rbtnSum() { // 점수 계산
			for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
				for(j=0;j<5;j++){
					if(rbtn[i][j].isSelected()){
						if(i==0 || i ==5){ //scale[0]에 점수 ++
							// 몇점? 5-j점. 
							scale[0] += (5-j);
						}
						else if(i==1 || i ==6){ //scale[1]에 점수 ++
							scale[1] += (5-j);
						}
						else if(i==2 || i ==7){ //scale[2]에 점수 ++
							scale[2] += (5-j);
						}
						else if(i==3 || i ==8){ //scale[3]에 점수 ++
							scale[3] += (5-j);
						}
						else if(i==4 || i ==9){ //scale[4]에 점수 ++
							scale[4] += (5-j);
						}
					}
				}
			}
		}
	}
	
	/*--------------------------------------------------------------------------------------------- 프레임 4 ------------------------------------------------------------------------------*/
	class Frame4 extends JFrame {
		Container contentPane;
		int i=0, j=0;
		Frame5 frame5;// = new Frame5();
		//라디오버튼 생성
		JRadioButton rbtn[][] = new JRadioButton[10][5];
		Frame4() {
			setTitle("충남대 공과대학 학생대상 인성검사");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			contentPane = getContentPane(); // 컨탠트팬 알아내기
			
			//contentPane.setLayout(new FlowLayout()); // ☆그리드 레이아웃 제대로 설정이 안됨
			contentPane.setLayout(null);
			
			JLabel title = new JLabel(" 문제＼답변"); // 문제＼답변
			title.setBounds(0, 0, 630,63); // 위치와 사이즈 지정
			title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			
			JLabel answers[] = new JLabel[5];
			String[] answer = {"매우 그렇다", "그렇다", "보통이다", "아니다", "매우 아니다"};
			// 기본 답변 부착
			for(i=0;i<5;i++){
				answers[i] = new JLabel(answer[i], SwingConstants.CENTER); // 중앙정렬
				answers[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				answers[i].setLocation(630+(75*i),0);
				answers[i].setSize(75,63);
				this.add(answers[i]);
			}
			
			//기본 질문 부착
			JLabel questions[] = new JLabel[10];
			String[] question = {" 21. 팀의 이익을 우선시 할 때도 있고, 내 이익을 우선시할 때도 있다."," 22. 높은 목표를 설정한다."," 23. 다른 사람들과 비슷한 정도의 평가는 받아야 한다.",
					" 24. 출세를 위해 아부 하는 것은 그다지 잘못된 것이 아니다."," 25. 성공을 위해 일하는 것을 좋아한다.", " 26. 무언가를 얻기 위해 아부를 하지 않는다."," 27. 불가능해 보이는 목표라도 끊임없이 도전한다.",
					" 28. 공정하지 않은 사람이라도 내게 도움이 된다면 친해질 용의가 있다."," 29. 내 자신의 생각이나 기분을 솔직하게 표현하는 편이다.", " 30. 팀워크를 강조하는 상황보다 경쟁을 강조하는 상황을 선호한다."};
			
			for(i=0;i<10;i++) {
				questions[i] = new JLabel(question[i]);
				// 폰트 설정 questions[i].setFont(new Font("Arial", Font.PLAIN, 10));
				questions[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				questions[i].setBounds(0,63+(63*i),630, 63);
				this.add(questions[i]);
			}
			
			
			//라디오 버튼 그룹 객체 생성
			ButtonGroup[] g = new ButtonGroup[10];
			
			//라디오패널 생성
			JPanel radioPanel[] = new JPanel[10];
			//라디오 그룹에 버튼 묶기
			for(i=0;i<10;i++) {
				g[i] = new ButtonGroup();
				radioPanel[i] = new JPanel(); 
				radioPanel[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				radioPanel[i].setBounds(630,63+(63*i),375,63);
				radioPanel[i].setLayout(new GridLayout(0, 5));
				//radioPanel[i].setBackground(Color.blue);
				for(j=0;j<5;j++) {
					rbtn[i][j] = new JRadioButton();
					g[i].add(rbtn[i][j]); //그룹묶기
					
					radioPanel[i].add(rbtn[i][j]);
					// Will rbtn 부착
					
					// Will 리스너 부착해야함 
				}
				this.add(radioPanel[i]);
			}
			
			JButton backbtn = new JButton("BACK");
			backbtn.setBounds((1024/2 - 95), 695, 80, 30);
			JButton nextbtn = new JButton("ENTER");
			nextbtn.setBounds((1024/2 + 15), 695, 80, 30);	
			this.add(title);
			this.add(backbtn);
			this.add(nextbtn);
			
			backbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					
						frame1.frame2.frame3.frame4.setVisible(false);
						frame1.frame2.frame3.setVisible(true);
						repaint();
				}
			});
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					int k = 0;
					for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
						for(j=0;j<5;j++){
							if(rbtn[i][j].isSelected()){
								k++; // k = 선택된 라디오버튼 갯수
							}
						}
					}
					if(k==10){
						frame1.frame2.rbtnSum();
						frame1.frame2.frame3.rbtnSum();
						frame1.frame2.frame3.frame4.rbtnSum();
						System.out.println("scale[0]: " + scale[0] + "\nscale[1]: " + scale[1] + "\nscale[2]: " + scale[2] + "\nscale[3]: " + scale[3] + "\nscale[4]: "+scale[4]);
					
						frame5 = new Frame5();
						frame1.frame2.frame3.frame4.setVisible(false);
						frame5.setVisible(true);
						repaint();
						
					}
					else{
						JOptionPane.showMessageDialog(null, "항목에 모두 체크하세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				     close();
				    }
			});
			setSize(1024, 768);
			setVisible(false);
			setLocation(50,50);
		}
		
		public void close(){
			if(JOptionPane.showConfirmDialog(null,"정말로 종료하시겠습니까?","인성검사",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
				return;
			}
			System.exit(0);
		}
		
		public void rbtnSum() { // 점수 계산
			for(i=0;i<10;i++){ // 선택된 라디오버튼 갯수가 10개가 맞으면 다음 프레임으로
				for(j=0;j<5;j++){
					if(rbtn[i][j].isSelected()){
						if(i==0 || i ==5){ //scale[0]에 점수 ++
							// 몇점? 5-j점. 
							scale[0] += (5-j);
						}
						else if(i==1 || i ==6){ //scale[1]에 점수 ++
							scale[1] += (5-j);
						}
						else if(i==2 || i ==7){ //scale[2]에 점수 ++
							scale[2] += (5-j);
						}
						else if(i==3 || i ==8){ //scale[3]에 점수 ++
							scale[3] += (5-j);
						}
						else if(i==4 || i ==9){ //scale[4]에 점수 ++
							scale[4] += (5-j);
						}
					}
				}
			}
		}
	}
	/*--------------------------------------------------------------------------------------------- 프레임 5 ------------------------------------------------------------------------------*/
	//프레임 5에서 종료
	class Frame5 extends JFrame {
		Container contentPane;
		
		Frame5() {
			setTitle("충남대 공과대학 학생대상 인성검사");
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			contentPane = getContentPane(); // 컨탠트팬 알아내기
			contentPane.setLayout(new BorderLayout());
			
			
			JButton nextbtn = new JButton("HOME (새로 검사하기) ");	
			MyPanel mainPanel = new MyPanel();
			JPanel southPanel = new JPanel();
			southPanel.setLayout(new FlowLayout());
			southPanel.add(nextbtn);
			
			this.add(mainPanel, BorderLayout.CENTER);
			this.add(southPanel, BorderLayout.SOUTH);
			
			createToolBar(); // 툴바 달기
			
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					frame1.frame2.frame3.frame4.frame5.setVisible(false);
					//frame1.setVisible(true);
					
					savedCompany = "건축학과";
					savedNumber = "";
					savedName = "";
					
					System.out.println("--초기화-- \n저장된 학과 : " + savedCompany);
					System.out.println("저장된 학번 : " + savedNumber);
					System.out.println("저장된 이름 : " + savedName); //설문 종료로 저장된 이름 학번 지움
					
					//scale 값 초기화
					scale[0] = 70;
					scale[1] = 70;
					scale[2] = 70;
					scale[3] = 70;
					scale[4] = 70;
					
					new Survey2();
					repaint();
				}
			});
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
				     close();
				    }
			});
			setSize(1024, 768);
			setVisible(false);
			setLocation(50,50);
		}
		
		public void close(){
			if(JOptionPane.showConfirmDialog(null,"정말로 종료하시겠습니까?","인성검사",JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION){
				return;
			}
			System.exit(0);
		}
		
		void createToolBar() {
			// 툴바 생성
			JToolBar toolBar = new JToolBar("Tool Bar"); // 툴바의 이름
			toolBar.setBackground(Color.LIGHT_GRAY);
			toolBar.setFloatable(false); // 툴바 이동 불가능
			//toolBar.setLayout(new FlowLayout());
			
			/* 툴바에 넥스트 버튼 추가
			JButton nextbtn = new JButton("HOME (새로 검사하기) ");
			
			nextbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					frame1.frame2.frame3.frame4.frame5.setVisible(false);
					frame1.setVisible(true);
					
					//savedCompany = "건축학과";
					savedNumber = "";
					savedName = "";
					
					System.out.println("--초기화-- \n저장된 학과 : " + savedCompany);
					System.out.println("저장된 학번 : " + savedNumber);
					System.out.println("저장된 이름 : " + savedName); //설문 종료로 저장된 이름 학번 지움
					
					repaint();
				}
			});
			
			toolBar.add(nextbtn);*/
			toolBar.addSeparator();
			JButton save = new JButton("저장하기", new ImageIcon("images/save.jpg"));
			// 저장 버튼 선택시
			save.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					try {
						FileWriter fout = new FileWriter("db/" +savedNumber+"_"+savedName+ ".txt");
						String intToString[] = new String[5];
						
						fout.write(Integer.toString(scale[0])+"\n");
						fout.write(Integer.toString(scale[1])+"\n");
						fout.write(Integer.toString(scale[2])+"\n");
						fout.write(Integer.toString(scale[3])+"\n");
						fout.write(Integer.toString(scale[4]));
						
			            fout.close();
			         }
					catch (Exception ex) {}
				}
			}); 
			
			toolBar.add(save);
			toolBar.addSeparator();
			
			JButton print = new JButton("인쇄하기");
			
			// 프린트 버튼 클릭 시 
			/*print.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
				
				}
			});*/
			toolBar.add(new JButton("인쇄하기"));
			
			int i;
			for(i=0;i<35;i++){
				toolBar.addSeparator();
			}
			
			
			
			JLabel party = new JLabel("학과 : ");
			JTextField partybox = new JTextField(savedCompany);
			partybox.setEditable(false);
			JLabel number = new JLabel("학번 : ");
			JTextField numberbox = new JTextField(savedNumber);
			numberbox.setEditable(false);
			JLabel name = new JLabel("이름 : ");
			JTextField namebox = new JTextField(savedName);
			namebox.setEditable(false);
			
			toolBar.add(party);
			toolBar.add(partybox);
			toolBar.add(number);
			toolBar.add(numberbox);
			toolBar.add(name);
			toolBar.add(namebox);
			
			contentPane.add(toolBar, BorderLayout.NORTH);
		}
	}
	
	class MyPanel extends JPanel {
		int i;
		MyPanel() {
			
			this.setLayout(null);
			//this.setBackground(Color.WHITE);
			
			Font f = new Font("굴림체", Font.PLAIN, 30);
			
			JLabel title = new JLabel("인성검사 결과"); // 글자라벨 붙이기
			title.setFont(f);
			title.setBounds(334, 10, 300,50);
			this.add(title);
			
			JLabel graph = new JLabel("<인성검사 막대그래프>"); // 글씨 라벨
			graph.setBounds(121, 470, 150, 25);
			this.add(graph);
			
			JLabel tablename = new JLabel("<인성검사 결과표>"); // 글씨 라벨
			tablename.setBounds(629, 570, 150, 25);
			this.add(tablename);
			
			// 표만들기
			JLabel table = new JLabel(); // 테이블 큰 테두리
			table.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			table.setBounds(434,80,520,450);
			this.add(table);
			
			JLabel stable[] = new JLabel[5];
			String scaletx[] = {"사회성","도덕성","협력성","창의성","적응성"};
			
			for(i=0;i<5;i++){
				stable[i] = new JLabel(scaletx[i], SwingConstants.CENTER);
				stable[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				stable[i].setBounds(434,80+(90*i),100,90);
				this.add(stable[i]);
			}
			
			JTextArea body[] = new JTextArea[5];
			String good[] = {
					"\n 사회성이 좋은 편입니다.\n 대인과의 관계가 원만하고 문제가 없습니다.",
					"\n 도덕의식이 뚜렷합니다.\n 옳고 그름을 바르게 인지하고 있으며 이를 실천하는데 무리가 없습니다",
					"\n 협력성이 남다릅니다.\n 타인과의 어울리길 좋아하며 같이 일하는데 지장이 없습니다", 
					"\n 창의성이 뛰어납니다.\n 창의성이 뛰어나 어떤 문제든지 창의적인 사고로 해결해 나갈 수 있습니다.", 
					"\n 적응성이 좋습니다.\n 어떤 환경에서도 문제없이 자신의 일을 꿋꿋히 해낼 수 있으리라 판단됩니다"
			};
			String middle[]  = {
					"\n 사회성이 적당합니다.\n 대인과의 관계가 비교적 원만한 편이나 좀더 적극적일 필요가 있습니다.",
					"\n 도덕성이 양호합니다.\n 옳고 그름을 판단할 수 있으나 이를 실천함에 있어 조금 부족합니다.",
					"\n 협력성이 양호한 편입니다.\n 타인과의 상호작용이 양호한 편입니다.", 
					"\n 창의성이 보통입니다.\n 창의성이 보통 수준입니다.", 
					"\n 적응성이 괜찮은 편입니다.\n 낯선 환경에서도 시간이 조금만 지나면 곧잘 적응할 수 있습니다." 
			};
			String bad[] = {
					"\n 사회성이 부족합니다.\n 다른 사람과의 관게에서 좀 더 적극적일 필요가 있습니다.",
					"\n 도덕성이 부족합니다.\n 준법의식과 도덕관념을 더욱 키울 필요가 있습니다.",
					"\n 협력성이 부족합니다.\n 타인과의 의사소통에 더욱 적극적일 필요가 있습니다.", 
					"\n 창의성이 부족합니다.\n 좀 더 창의적인 사고가 필요합니다.", 
					"\n 적응성이 부족합니다.\n 낯선 환경을 피하지 말고 좀 더 적극적일 필요가 있습니다."
			};
			
			for(i=0;i<5;i++){
				if(scale[i] >=90) {
					body[i] = new JTextArea(good[i]);
				}
				else if(scale[i] >=80) {
					body[i] = new JTextArea(middle[i]);
				}
				else {
					body[i] = new JTextArea(bad[i]);
				}
				//body[i].setEditable(false);
				body[i].setBounds(534,80+(90*i),420,90);
				body[i].setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				this.add(body[i]);
			}
			
			// 그래프 그리기
			BoxLabel [] boxes = new BoxLabel[5];
			String [] boxNames = {"사회성","도덕성","협력성","창의성","적응성"};
			String [] graphs = {"0","25","50","75","100"};
			
			for(i=0;i<5;i++) {
				boxes[i] = new BoxLabel(scale[i]);
				boxes[i].setBounds(51+(60*i), 120, 50, 300);
				
				JLabel text1 = new JLabel(boxNames[i]); // 재료통 밑에 출력할 문자열
				text1.setLocation(51+(60*i), 420);
				text1.setSize(50, 30);
				
				JLabel text2 = new JLabel(graphs[i]); // 재료통 밑에 출력할 문자열
				text2.setLocation(21, 405-(75*i));
				text2.setSize(30, 30);
				
				this.add(text2);
				this.add(text1);
				this.add(boxes[i]);
			}
			
			
			
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);
			g.drawLine(1024/8*3, 80, 1024/8*3, 570); //가운데 선
			g.drawLine(50, 120, 50, 420); // y축 선
			g.drawLine(46, 420, 346, 420); // x축 선
			g.drawLine(46, 345, 51, 345); // 25 표시선
			g.drawLine(46, 270, 51, 270); // 50 표시선
			g.drawLine(46, 195, 51, 195); // 75표시선
			g.drawLine(46, 120, 51, 120); // 100 표시선
		}
	}
	
	class BoxLabel extends JLabel { //그래프 레이블
		final int MAXSIZE = 100; // 통 크기
		int currentSize; // 현재 점수
		
		public BoxLabel() {
			currentSize = 0;
		}
		public BoxLabel(int x) {
			currentSize = x;
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			// LIGHT_GRAY 색으로 남아 있는 양을 보여 주기위해 그린다.
			g.setColor(Color.LIGHT_GRAY);
			int y = this.getHeight() - (currentSize*this.getHeight() / MAXSIZE);
			g.fillRect(0, y, this.getWidth(), this.getHeight() - y);
			
			/*// GRAY 색으로 통의 외곽선을 그린다.			
			g.setColor(Color.GRAY);
			g.drawRect(0, y, this.getWidth()-1, this.getHeight()-y-1);*/
			
			// GRAY 색으로 통의 외곽선을 그린다.			
						g.setColor(Color.GRAY);
					    g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);	
		}
	}
	
	public static void main(String[] args) {
		new Survey2();
	}

}