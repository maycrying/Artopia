package com.artopia;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.UIManager;

public class Functions {
	
	//��ȡ�ƶ����ڵ�����
	public String getWeekOfDate(Date dt) {
        String[] weekDays = {"������","����һ", "���ڶ�", "������", "������", "������", "������"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day < 0)
            day = 0;
        return weekDays[day];
    }
	
	//�Ƚ�2��������֮��������
	public int getNumofDay(String bookday,String classday) {
		
		String[] weekDays = {"������","����һ", "���ڶ�", "������", "������", "������", "������"};
		int index1=0,index2=0,daynum =0;
		for(int i=0;i<weekDays.length;i++) {
			if(weekDays[i].equals(bookday)) {
				index1 = i;
			}
			if(weekDays[i].equals(classday)) {
				index2 = i;
			}
		}
		if(index1<index2) {
			daynum= index2-index1;
		}
		else{
			daynum=(7-index1)+index2;
		}
			
		return daynum;
	}
	
	public String getNextDate(String day,int index) {
		//��ȡʱ��εĿ�ʼʱ�䣬ǰ8λΪxx:xx:xx
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
				
		cal.add(Calendar.DAY_OF_MONTH, getNumofDay(getWeekOfDate(new Date()), day)+7*index);
		return (dateFormat.format(cal.getTime()));
	
		
	}

	//�����ݿ��ѯ�����������
	public String[] getList(List<String> list) {
		
		if(list != null && list.size()>0){//���list�д��������ݣ�ת��Ϊ����  
		    String[] array=new String[list.size()];//����һ����list����һ��������  
		    for(int i=0;i<list.size();i++){  
		    	array[i]=list.get(i);//���鸳ֵ�ˡ�  
		    }
		return array;  
		}
		
		return null;
	}
	
	//ȥ���������ظ�����
	public String[] noDuplicate(String[] array) {
		
	    List<String> list = new ArrayList<String>();
	    for(int i=0; i<array.length; i++)
	    {
	    if(!list.contains(array[i]))
	    list.add(array[i]);
	    }
	    
	    return getList(list);
	}
	
	public void addItems(JComboBox<Object> jComboBox,String[] array) {
		
		for(int i=0;i<array.length;i++) {
			jComboBox.addItem(array[i]);
		}
		
	}
	
	public String[] getNewTimes(String day, String[] times) {
		String[] newtimes = new String[times.length*4];
		for(int i=0;i<times.length;i++) {
			for(int j=0;j<4;j++) {
				newtimes[i*4+j]=getNextDate(day, j)+" "+times[i].substring(0, times[i].indexOf("-"));
			}
		}
		
		return newtimes;
	}
	
	public void setOptionPaneFont() {
		UIManager.put("OptionPane.messageFont", new Font("΢���ź�", Font.BOLD, 14));
		UIManager.put("OptionPane.buttonFont", new Font("΢���ź�", Font.PLAIN, 12));
	}

	public Object[][] getArrays(Vector<Vector<Object>> v) {
		// TODO Auto-generated method stub
		Object[][] data =new Object[v.size()][v.get(0).size()];
		for(int i=0;i<v.size();i++) {
			for(int j=0;j<v.get(0).size();j++) {
				data[i][j] = v.get(i).get(j);
			}
		}
		return data;
	}

}

