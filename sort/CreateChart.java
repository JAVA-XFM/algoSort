package sort;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class CreateChart {
	
	public static void main(String[] args) {
		//创建一个数据集对象
        DefaultCategoryDataset dataset=null;

        //设置数据集
        dataset=new DefaultCategoryDataset();
        //第一个参数是第三个参数的值，即“最高分”，第二个参数表示目录轴的分类，第三个参数表示的x轴显示标签
        int[] array = new int[100];
        int index = 0;
        int value;
        while(index < 100) {
        	if(isExist(array,value = (int)(Math.random()*100 + 1))) {
        		array[index] = value;
        		index++;
        	}
        }
        
        for (int i : array) {
			System.out.print(i+",");
		}
        
        
        for (int i = 0; i < array.length; i++) {
			dataset.addValue(array[i], "sort", i+"");
		}

        // 创建简单的条形图
        JFreeChart freeChart=ChartFactory.createBarChart(
                "",// 图表标题
                "",// 水平轴的显示标签
                "",//垂直轴的显示标签
                dataset,//数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL,//图表方向：水平，垂直
                true,//是否显示图例
                false,//是否显示提示
                false//是否生成URL连接
        );
        //以面板显示，创建一个图表面板
        ChartPanel chartPanel=new ChartPanel(freeChart);
        //设置大小
        chartPanel.setPreferredSize(new java.awt.Dimension(560,400));

        //创建一个主窗口来显示面板
        JFrame frame=new JFrame("条形图");
        frame.setLocation(500,400);
        frame.setSize(600,500);

        //将图表面板设置为主窗口的内容面板
        frame.setContentPane(chartPanel);

        //显示主窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
	}
	
	public static boolean isExist(int[] array, int value) {
	
		for (int i = 0; i < array.length; i++) {
			if(array[i] == value) {
				return false;
			}
		}
		
		return true;
	}
	
}
