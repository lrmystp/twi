package form;

import play.data.validation.Constraints.*;

public class TweetForm {
    @Required
    public String content;
}
