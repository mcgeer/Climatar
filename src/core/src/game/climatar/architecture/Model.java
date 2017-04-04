package game.climatar.architecture;

import java.util.HashMap;
import java.util.Map;

public abstract class Model {

	private final Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * @param propertyId
	 *            Property identifier
	 * @return The property
	 */
	public Object get(String propertyId) {
		return map.get(propertyId);
	}

	/**
	 * Sets a property for this model.
	 * 
	 * @param propertyId
	 *            Property identifier
	 * @param object
	 *            The property
	 * @return Property which was previously set to this propertyId
	 */
	public Object set(String propertyId, Object object) {
		Object oldProperty = map.put(propertyId, object);
		return oldProperty;
	}

}
