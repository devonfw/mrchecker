package mbt;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.model.Vertex;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestBuilder;
import org.junit.jupiter.api.Test;

import com.capgemini.mrchecker.test.core.BaseTest;

import graphs.SampleGraphImplementation;

public class SampleMBT extends BaseTest {
	
	public final static Path MODEL_PATH = Paths.get("graphs/SampleGraph.json");
	
	@Test
	public void runTest() {
		Result execution = new TestBuilder()
				.addContext(new SampleGraphImplementation().setNextElement(new Vertex().setName("v_Start")
						.build()),
						MODEL_PATH,
						new RandomPath(new ReachedVertex("v_End")))
				.execute(true);
		
		if (execution.hasErrors()) {
			List<String> errors = execution.getErrors();
			
			fail("ERROR IN EXECUTION: \"" + errors);
		}
	}
}
