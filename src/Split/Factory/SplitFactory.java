package Split.Factory;

import Split.Enums.SplitType;
import Split.EqualExpenseSplit;
import Split.Interfaces.ExpenseSplit;
import Split.PercentageExpenseSplit;
import Split.UnequalExpenseSplit;

public class SplitFactory {

    public static ExpenseSplit getSplitObject(SplitType splitType){
        switch (splitType){
            case EQUAL:
                return new EqualExpenseSplit();
            case UNEQUAL:
                return new UnequalExpenseSplit();
            case PERCENTAGE:
                return new PercentageExpenseSplit();
            default:
                System.out.println("Invalid splitType detected " + splitType);
                return null;
        }
    }
}
