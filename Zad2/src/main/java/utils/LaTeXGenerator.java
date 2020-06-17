package utils;

import java.util.List;

public class LaTeXGenerator {

    private int nrOfRows;
    private int nrOfCols;
    private List<String> colNames;
    private String caption;
    private String [][]content;
    private String [] quantifier = null;

    public void setQuantifier(String[] quantifier) {
        this.quantifier = quantifier;
    }

    public String generateColNumber() {
        String cols = "";
        for(int i = 0; i < nrOfCols; i++){
            cols += " c ";
        };
        return cols;
    };

    public String[][] getContent() {
        return content;
    }

    public void setContent(String[][] content) {
        this.content = content;
    }

    public int getNrOfRows() {
        return nrOfRows;
    }

    public void setNrOfRows(int nrOfRows) {
        this.nrOfRows = nrOfRows;
    }

    public int getNrOfCols() {
        return nrOfCols;
    }

    public void setNrOfCols(int nrOfCols) {
        this.nrOfCols = nrOfCols;
    }

    public List<String> getColNames() {
        return colNames;
    }

    public void setColNames(List<String> colNames) {
        this.colNames = colNames;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public LaTeXGenerator(int nrOfRows, int nrOfCols, List<String> colNames, String caption, String[][] content) {
        this.nrOfRows = nrOfRows;
        this.nrOfCols = nrOfCols;
        this.colNames = colNames;
        this.caption = caption;
        this.content = content;
    }

    public String generateContent() {
        String text = "";
        String [][] content = getContent();
        for(int i = 0; i < nrOfRows; i++) {
            text+= quantifier[i] + " & ";
            for (int j = 0; j < nrOfCols - 1; j++) {
                if(j == nrOfCols - 2) {
                    text += content[i][j] + " \\\\ \n";
                } else {
                    text += content[i][j] + " & ";
                }
            }
        }
        return text;
    }

    public String generateContentWithoutQuantifiers() {
        String text = "";
        String [][] content = getContent();
        for(int i = 0; i < nrOfRows; i++) {
            for (int j = 0; j < nrOfCols; j++) {
                if(j == nrOfCols - 1) {
                    text += content[i][j] + " \\\\ \n";
                } else {
                    text += content[i][j] + " & ";
                }
            }
        }
        return text;
    }

    public String generateColNames() {
        String columnNames = "";
        for(int i = 0; i < colNames.size(); i++) {
            columnNames += " " + colNames.get(i) + " ";
            if(i < colNames.size() - 1) {
                columnNames+= "&";
            } else {
                columnNames+=" \\\\";
            }
        }
        return columnNames;
    }

    public String generateLaTeXTable() {
        String result = "";
        String content = quantifier != null ? generateContent() : generateContentWithoutQuantifiers();
        result += "\\begin{table}[!htbp] \n\\centering\n" +
                "\\begin{tabular}{||"+ generateColNumber() +"||}" +
                "\n\\hline\n" + generateColNames() + "\n\\hline \\hline \n"+ content +
                "\\hline\n\\end{tabular}\n \\caption{"+getCaption()+"}\n" +
                "\\centering\n\\end{table}";

        return result;
    };
}
