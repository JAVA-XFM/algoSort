package sort;

import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Test extends JFrame {
	private JButton btn = new JButton("排序");
	//private static TextArea ta = new TextArea(12, 70);
	private int[] arr = { 80,90,38,54,64,93,76,8,57,73,62,33,24,
					23,86,9,100,53,22,21,7,81,95,89,78,2,26,3,70,17,
					58,4,82,98,29,5,56,44,47,32,16,6,50,42,46,27,19,
					43,12,52,15,28,49,75,72,88,99,85,69,41,84,59,68,
					14,40,37,87,55,60,34,31,65,11,30,79,39,25,66,97,
					83,61,96,20,18,51,1,67,92,77,74,36,13,91,63,10,
					45,35,94,48,71};

	private static  DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	private static JFreeChart freeChart = null;
	private static ChartPanel chartPanel = null;
	
	public Test() {

		setSize(550, 300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout());
		add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bubbleSort(arr);
			}
		});
		//add(ta);
		if(chartPanel != null) {
			setContentPane(chartPanel);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

	public static int[] bubbleSort(int[] source) {
		boolean isSort = false; // 是否排序
		for (int i = 1; i < source.length; i++) {
			isSort = false; // 在每次排序前都初始化为false
			//ta.append("---------------第" + i + "次排序【" + arrayToString(source) + "】---------------\n");
			for (int j = 0; j < source.length - i; j++) {
				if (source[j] > source[j + 1]) {
					int temp = source[j];
					source[j] = source[j + 1];
					source[j + 1] = temp;
					isSort = true; // 为TRUE表明此次循环（外层循环）有排序。
					//ta.append("第" + i + "次排序，第" + (j + 1) + "次比较。" + source[j] + "与" + source[j + 1] + "交换位置【"
					//		+ arrayToString(source) + "】\n");
					
					for (int k = 0; k < source.length; k++) {
						dataset.addValue(source[k], "sort", source[k]+"");
					}
					freeChart=ChartFactory.createBarChart(
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
					chartPanel=new ChartPanel(freeChart);
					//设置大小
			        chartPanel.setPreferredSize(new java.awt.Dimension(560,400));
			        try {
						Thread.currentThread().sleep(1000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (!isSort) {
				//ta.append("排序完毕！\n");
				break; // 如果没有排序，说明数据已经排序完毕。
			}
		}
		return source;
	}

	public static String arrayToString(int[] arr) {
		if (arr == null || arr.length == 0) {
			return "";
		}
		StringBuilder sbr = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			sbr.append(arr[i]);
			if (i != arr.length - 1) {
				sbr.append(",");
			}
		}
		return sbr.toString();
	}
}