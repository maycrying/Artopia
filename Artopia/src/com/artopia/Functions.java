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
	
	//获取制定日期的星期
	public String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期天","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int day = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (day < 0)
            day = 0;
        return weekDays[day];
    }
	
	//比较2个星期数之间差的天数
	public int getNumofDay(String bookday,String classday) {
		
		String[] weekDays = {"星期天","星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
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
		//获取时间段的开始时间，前8位为xx:xx:xx
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
				
		cal.add(Calendar.DAY_OF_MONTH, getNumofDay(getWeekOfDate(new Date()), day)+7*index);
		return (dateFormat.format(cal.getTime()));
	
		
	}

	//将数据库查询结果放入数组
	public String[] getList(List<String> list) {
		
		if(list != null && list.size()>0){//如果list中存入了数据，转化为数组  
		    String[] array=new String[list.size()];//创建一个和list长度一样的数组  
		    for(int i=0;i<list.size();i++){  
		    	array[i]=list.get(i);//数组赋值了。  
		    }
		return array;  
		}
		
		return null;
	}
	
	//去除数组中重复的项
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
		UIManager.put("OptionPane.messageFont", new Font("微软雅黑", Font.BOLD, 14));
		UIManager.put("OptionPane.buttonFont", new Font("微软雅黑", Font.PLAIN, 12));
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

