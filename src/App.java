import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Perceptron Training
 */
public class App
{


	//    private static final String linearlySeparableData = "C:\\BellevueCollege\\Courses\\CS460\\Examples\\ANNs\\TrainingData\\PerceptronTrainingData_LinearlySeparable.txt";
//    private static final String linearlyInseparableData = "C:\\BellevueCollege\\Courses\\CS460\\Examples\\ANNs\\TrainingData\\DeltaRule_NotLinearlySeparable.txt";
	private static final String linearlySeparableData = "/Users/shymacbook/Documents/BC/cs460_ML/Practice/Perceptron/PracticeMaterials/PerceptronTraining/PerceptronTrainingData_LinearlySeparable.txt";
	private static final String linearlyInseparableData = "/Users/shymacbook/Documents/BC/cs460_ML/Practice/Perceptron/PracticeMaterials/PerceptronTraining/DeltaRule_NotLinearlySeparable.txt";
	public static void main( String[] args )
	{
		try
		{
			System.out.println("~~~ hello world");
			String[] trainingInstances = readFromFile(linearlySeparableData);

			train(trainingInstances);
			System.out.println("DONE");
		}
		catch(Exception ex)
		{
			System.out.println(String.format("ERROR: %s", ex.getMessage()));
		}
	}

	private static void train(String[] trainingData)
	{
		double w0 = 0.01;
		double w1 = 0.01;
		double w2 = 0.01;

		double learningRate = 0.001;  // NOTE:  try different values for this

		System.out.println(String.format("Initializing weights to:  w0=%.2f, w1=%.2f, w2=%.2f", w0, w1, w2));
		System.out.println(String.format("learningRate=%f", learningRate));

		double x1;
		double x2;
		double target;
		// Your training code goes here
		int missCount = 0;
		do{
			for (int i = 0; i <= trainingData.length; i++){
				String thisLine = trainingData[i];
				String[] token = thisLine.split(",");
				x1 = Double.parseDouble(token[0]);
				x2 = Double.parseDouble(token[1]);
				target = Double.parseDouble(token[2]);
				System.out.println("string:\t" + thisLine + "\t=\tx1: " + x1 + "\tx2: " + x2 + "\ttarget: " + target);

				double O_calc = (w0 * 1) + (w1 * x1) + (w2 * x2);

				if (O_calc != target){
					// ... update delta weights here...
					double delta_w0 = learningRate * (target - O_calc) * 1;
					double delta_w1 = learningRate * (target - O_calc) * x2;
					double delta_w2 = learningRate * (target - O_calc) * x2;
					w0 += delta_w0;
					w1 += delta_w1;
					w2 += delta_w2;
					missCount ++;
					System.out.println("Miss Count = " + missCount);
				}
				else if (O_calc == target){
					missCount = 0;
				}
			}
		} while (missCount > 0);


		System.out.println("out of while loop...");
		System.out.println(String.format("Finished training:  w0=%f, w1=%f, w2=%f", w0, w1, w2));

		double slope = -w1 / w2;
		double intercept = -w0 / w2;

		System.out.println(String.format("Equation of boundery: X2 = %f X1 + %f", slope, intercept));
	}





	private static String[] readFromFile(String path) throws FileNotFoundException, IOException
	{
		ArrayList<String> list = new ArrayList<String>();

		BufferedReader br = new BufferedReader(new FileReader(path));
		String line = br.readLine();
		while (line != null) {
			list.add(line);
			line = br.readLine();
		}

		br.close();

		String[] trainingData = list.toArray(new String[0]);

		System.out.println(String.format("Training data (%d instances) read from file %s", trainingData.length, path));
		return trainingData;
	}

}
