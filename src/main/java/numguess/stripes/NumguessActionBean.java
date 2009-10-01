package numguess.stripes;

import static numguess.model.Constants.FORWARD_RESTART;
import static numguess.model.Constants.FORWARD_RIGHT;
import static numguess.model.Constants.FORWARD_WRONG;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import numguess.model.GameModel;
import numguess.model.GuessResult;

/**
 * An action that depends on a game model instance.
 */
public class NumguessActionBean implements ActionBean {

    private ActionBeanContext context;

    private GameModel model;

    @Validate(required=true, expression="min <= this && this <= max", on="submitGuess")
    private int guess;

    private GuessResult result;

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    @Override
    public void setContext(final ActionBeanContext context) {
        this.context = context;
    }

    @SpringBean("gameModel")
    public void injectGameModel(final GameModel model) {
        this.model = model;
    }

    protected GameModel getGameModel() {
        return model;
    }

    public int getMin() {
        return getGameModel().getMin();
    }

    public int getMax() {
        return getGameModel().getMax();
    }

    public void setGuess(final int guess) {
        this.guess = guess;
    }

    public int getGuess() {
        return guess;
    }

    public GuessResult getResult() {
        return result;
    }

    protected void setResult(final GuessResult result) {
        this.result = result;
    }

    public Resolution submitGuess() {
        // interact with model
        setResult(getGameModel().guess(getGuess()));
        // choose forwarding target
        if (result.getComparison() == 0) {
            return new ForwardResolution(FORWARD_RIGHT);
        } else {
            return new ForwardResolution(FORWARD_WRONG);
        }
    }

    @DefaultHandler
    public Resolution restart() {
        // interact with model
        getGameModel().reset();
        System.out.println("locale = " + context.getLocale());
        // choose forwarding target
        return new ForwardResolution(FORWARD_RESTART);
    }
}
