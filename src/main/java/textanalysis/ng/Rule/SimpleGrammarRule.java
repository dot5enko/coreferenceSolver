package textanalysis.ng.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import textanalysis.ng.NormalizationType;
import textanalysis.ng.ParserToken;
import textanalysis.ng.RuleLabel;

public class SimpleGrammarRule implements GrammarRuleI {

    public boolean active = true;

    public boolean terminal = false;
    public boolean repeatable = false;
    public boolean optional = false;
    public boolean skip = false;
    public List<RuleLabel> labels = new ArrayList();
    public NormalizationType normalization = null; // TODO fix this
    public String interpretation = ""; // todo fix this

    public SimpleGrammarRule(boolean terminal) {
        this.terminal = terminal;
    }
     
    public SimpleGrammarRule(RuleLabel... labels) {
        this.labels = Arrays.asList(labels);
    }

    public SimpleGrammarRule(SimpleGrammarRule copy) {
        
        this.active = copy.active;
        this.terminal = copy.terminal;
        this.optional = copy.optional;
        this.repeatable = copy.repeatable;
        this.skip = copy.skip;
        
        this.labels = copy.labels;
    }

    @Override
    public boolean isSimple() {
        return true;
    }

    @Override
    public boolean active() {
        return this.active;
    }

    /**
     * Local stack is null because we don't have info about stack here. so no gnc_match or similar can be used here
     * @param pt
     * @return 
     */
    public boolean checkLocal(ParserToken pt) {
        for (RuleLabel rl : this.labels) {
            if (!rl.check(pt, null)) {
                return false;
            } 
        }
        
        return true;
        
    }
}
