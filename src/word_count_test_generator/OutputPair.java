/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package word_count_test_generator;

/**
 *
 * @author sbvb
 */
public class OutputPair {
    int i;
    String str;
    
    OutputPair(int i, String str) {
        this.i = i;
        this.str = str;
    }
    
    @Override
    public String toString() {
        return "" + i + ":" + str;
    }
    
}
