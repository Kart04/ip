/**
 * Represents a custom checked for the Spped application
 * <P>
 * This excption is thrown to indicate application-specific error conditions
 * that a program might want to catch and handle similar to other subclasses
 * of {@link Exception} in Java
 *
 * @see java.lang.Exception
 * @see java.lang.Throwable
 */

package errorcorrection;

public class SpeedException extends Exception {
    public SpeedException(String message) {
        super(message);
    }
}


