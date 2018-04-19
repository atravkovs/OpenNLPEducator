# Apache OpenNLP model educator
A simple tool that allows you to train OpenNLP model with just one command.

## How to start using it?
* Clone or download this repository from GitHub.
* Make sure that you have Java installed `java --version`, otherwise download it from [Java official website](https://java.com/en/download/)
* In `out/artifacts/OpenNLPEducator_jar` folder start Command Prompt and execute `java -jar OpenNLPEducator.jar input_file output_file training_iterations* cutoff*`, where:
    * __input_file__ - path to text file, that contains training data. Every new text for analysis should be on a new line and each line is represented as follows: `mark[Tab]text`
    * __output_file__ - path to file where trained model will be saved (usual file extension is `.bin`)
    * __training_iterations*__ - training iteration count (unnecessary parameter)
    * __cutoff*__ - cutoff parameter value (unnecessary parameter)