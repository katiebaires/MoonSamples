/************************************************************************
 * Project 5 - MoonSamples
 *************************************************************************
 * This project will help martians analyze the soil on Mars.
 * Katie Martinez
 * 10/31/2022
 * CMSC 255-001
 *************************************************************************/

//Create import scanner
import java.util.Arrays;
import java.util.ArrayList;

//input String array elements
//input String array sample values
//pass arguments to getElements and store results
//pass arguments to getSamples and store results
//call searchForLife, passing in the result og getSamples and print results
//call searchHighestElement, passing in the results of getSamples and getElements, and print results
//call searchHighestSample, passing in the results of getSamples and getElements, and print results
public class MoonSamples {
    public static void main(String[] args) {

        String[] elements = MoonSamples.getElements("carbon-dioxide,magnesium,sodium,potassium,chloride,water");
        double[][] sampleValues = MoonSamples.getSamples("8.3,4.5,6.7,2.3,12.5,4.5<>3.9,1.8,34.7,23.5,1.2,14.3<>6.7,7.4,1.5,18.4,7.2,23.7<>23.4,5.6,2.9,18.5,39.5,18.2<>15.4,5.3,27.4,9.8,3.8,27.4");
        System.out.println(Arrays.toString(elements));
        System.out.println("\nSampleValues:\n" + Arrays.deepToString(sampleValues));

        System.out.println("\nThe samples that contain life are: ");
        System.out.println(Arrays.toString(MoonSamples.searchForLife(sampleValues)));

        System.out.println("\nThe highest elements for sample 1 are ");
        System.out.println(MoonSamples.searchHighestElements(sampleValues, elements, 1));

        System.out.println("\nThe sample with the highest value of the element water is ");
        System.out.println(MoonSamples.searchHighestSample(sampleValues, elements, "water"));

    }

    //Create getElement method
    //Input elements in a String
    //return 1D String array containing list of elements
    public static String[] getElements(String inputElementString) {
        return inputElementString.split(",");
    }

    //Create getSamples method
    //input sample values into a String
    //return a 2D String array containing the elemental composition of each sample
    public static double[][] getSamples(String inputSamplesString) {
        String[] samples = inputSamplesString.split("<>");

        double[][] numbers = new double[samples.length][samples[0].split(",").length];
        int row = 0;
        for (String data : samples) {
            String[] value = data.split(",");
            for (int i = 0; i < value.length; i++) {
                numbers[row][i] = Double.parseDouble(value[i]);
            }
            row++;
        }
        searchForLife(numbers);
        return numbers;
    }

    //Create searchForLife method
    //input 2D double array of sample values
    //value needed is 300, if this value is attained or surpasses, sample number is returned in array
    //return array that is resized
    public static int[] searchForLife(double[][] samples) {
        int[] array = new int[samples.length];
        int num = 0;

        for (int i = 0; i < samples.length; i++) {
            double co2 = samples[i][0];
            double mg = samples[i][1];
            double na = samples[i][2];
            double k = samples[i][3];
            double cl = samples[i][4];
            double h2o = samples[i][5];

            double lifeFormula = (8 * co2) + (2 * mg) + na + (4 * k) +
                    cl + (5 * h2o);
            if (lifeFormula >= 300) {
                array[i] = i + 1;
                num++;
            }
        }

        int[] resized = new int[num];
        int index = 0;
        for (int j = 0; j < array.length; j++) {
            if (array[j] != 0) {
                resized[index] = array[j];
                index++;
            }

        }
        return resized;
    }

    //Create searchHighestElements method
    //input sample values, sample search in 2D double String array
    //return String with two of the highest elements
    public static String searchHighestElements(double[][] samples, String[] elements, int sampleNum) {
        int highest1values = 0;
        int highest2values = 0;
        double[] sample = samples[sampleNum - 1];

        for (int i = 0; i < sample.length; i++) {
            if (sample[i] > sample[highest1values]) {
                highest2values = highest1values;
                highest1values = i;
            } else if (sample[i] > sample[highest2values]) {
                highest2values = i;
            }
        }

        return elements[highest1values] + " and " + elements[highest2values];
    }

    //Create searchHighestSample method
    //input String array of elements, 2D double array of sample values, & an element to search
    //return sample number containing the highest amount of such element
    public static int searchHighestSample(double[][] samples, String[] elements, String element) {
        int elementIndex = -1;
        for(int i = 0; i < elements.length; i++){
            if(elements[i].equals(element)){
                elementIndex = i;
            }
        }
        if(elementIndex == -1){
            return -1;
        }
        int highestElementIndex = 0;
        for(int i = 0; i < samples.length; i++) {
            if (samples[i][elementIndex] > samples[highestElementIndex][elementIndex]) {
                highestElementIndex = i;
            }

        }

       return highestElementIndex + 1;
    }
}










