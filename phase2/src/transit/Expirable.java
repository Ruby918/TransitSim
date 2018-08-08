package transit;

import util.FormattedDate;

public interface Expirable {

  boolean isValid(FormattedDate dateUsed);
}
