// Copyright (c) 2021 Promineo Tech

package recipes.Exception; 

/**
 * This exception is used to turn a checked exception into an unchecked
 * exception.
 * 
 * The annotation @SuppressWarnings("serial") is used to turn off a warning
 * because this class does not declare a variable named serialVersionUID, when
 * the parent class does. Serialization is a process that turns a Java object
 * into a stream so that it can be sent across the network and reconstituted on
 * the other end. This application does not support Serialization so we don't
 * need to include the variable.
 * 
 * @author Promineo
 *
 */
@SuppressWarnings("serial")
public class DbException extends RuntimeException {

	public DbException() {
		
	}
  /**
   * Creates an exception with a message.
   * 
   * @param message The message.
   */
  public DbException(String message) {
    super(message);
  }

  /**
   * Creates an exception with a cause.
   * 
   * @param cause The causal exception.
   */
  public DbException(Throwable cause) {
    super(cause);
  }

  /**
   * Create an exception with a message and a cause.
   * 
   * @param message The message.
   * @param cause The causal exception.
   */
  public DbException(String message, Throwable cause) {
    super(message, cause);
  }
}
