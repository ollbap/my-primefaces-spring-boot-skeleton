package es.test.test_controllers.charts;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@ViewScoped
public class GraphicImageView {

	@SuppressWarnings("static-method")
	public StreamedContent getChart() throws IOException {
		// Chart
		JFreeChart jfreechart = ChartFactory.createPieChart("Cities", createDataset(), true, true, false);
		ByteArrayInputStream chartStream = createInMemoryChartStream(jfreechart);
		DefaultStreamedContent chart = new DefaultStreamedContent(chartStream, "image/png");
		chart.setName("Chart.png");
		return chart;
	}

	private static ByteArrayInputStream createInMemoryChartStream(JFreeChart jfreechart) throws IOException {
		BufferedImage objBufferedImage=jfreechart.createBufferedImage(600,800);
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		ImageIO.write(objBufferedImage, "png", bas);
		byte[] byteArray=bas.toByteArray();
		return new ByteArrayInputStream(byteArray);
	}

	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("New York", new Double(45.0));
		dataset.setValue("London", new Double(15.0));
		dataset.setValue("Paris", new Double(25.2));
		dataset.setValue("Berlin", new Double(14.8));

		return dataset;
	}
}