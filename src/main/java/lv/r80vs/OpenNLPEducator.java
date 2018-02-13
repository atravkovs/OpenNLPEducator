package lv.r80vs;

import java.io.*;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

/**
 * Created by Artjom Travkov on 1/16/2018.
 */

public class OpenNLPEducator {
    DoccatModel model;

    private String inputFile;
    private String outputFile;
    private int cutoff = 2;
    private int trainingIterations = 30;

    public static void main(String[] args) {
        OpenNLPEducator reviewCategorizer = new OpenNLPEducator();
        reviewCategorizer.inputFile = args[0];
        reviewCategorizer.outputFile = args[1];

        if (args.length == 3) {
            reviewCategorizer.trainingIterations = Integer.parseInt(args[2]);
        }

        if (args.length == 4) {
            reviewCategorizer.cutoff = Integer.parseInt(args[3]);
        }

        System.out.println("Training Data File: " + reviewCategorizer.inputFile);
        System.out.println("Trained Model: " + reviewCategorizer.outputFile);
        System.out.println("Training iterations: " + reviewCategorizer.trainingIterations);
        System.out.println("Cutoff: " + reviewCategorizer.cutoff);

        System.out.println("Start training model...");
        reviewCategorizer.trainModel();

        System.out.println("Saving the model...");
        reviewCategorizer.saveModel(reviewCategorizer.model);

        System.out.println("Work completed successfully!");
    }



    void saveModel(DoccatModel mModel) {
        try {
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream(outputFile));
            mModel.serialize(modelOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void trainModel() {
        InputStream dataIn = null;
        try {
            dataIn = new FileInputStream(inputFile);
            ObjectStream lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream sampleStream = new DocumentSampleStream(lineStream);

            model = DocumentCategorizerME.train("en", sampleStream, cutoff, trainingIterations);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dataIn != null) {
                try {
                    dataIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}