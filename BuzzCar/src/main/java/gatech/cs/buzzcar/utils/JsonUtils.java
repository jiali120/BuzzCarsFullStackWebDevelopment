package gatech.cs.buzzcar.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JacksonJsonParser;

import java.util.Map;

public class JsonUtils {

	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private JsonUtils(){}

	
	public static <T> String toJson(T obj) {
		String jsonStr = null;
		try {
			jsonStr = OBJECT_MAPPER.writeValueAsString(obj);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return jsonStr;
	}

	public static Map<String, Object> fromJson2Map(String json) {
		JacksonJsonParser p = new JacksonJsonParser();
		return p.parseMap(json);
	}


	public static <T> T fromJson(String json, Class<T> type) {
		T obj = null;
		try {
			obj = OBJECT_MAPPER.readValue(json, type);
		} catch (Exception e) {
			log.error("JSON converting Java error！json={}", json);
		}
		return obj;
	}

}