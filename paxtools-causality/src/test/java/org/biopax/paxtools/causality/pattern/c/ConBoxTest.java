package org.biopax.paxtools.causality.pattern.c;

import org.biopax.paxtools.causality.pattern.MappedConst;
import org.biopax.paxtools.causality.pattern.Match;
import org.biopax.paxtools.causality.pattern.Pattern;
import org.biopax.paxtools.causality.pattern.Searcher;
import org.biopax.paxtools.io.SimpleIOHandler;
import org.biopax.paxtools.model.BioPAXElement;
import org.biopax.paxtools.model.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Ozgun Babur
 */
public class ConBoxTest
{
	Model model;

	@Before
	public void setUp() throws Exception
	{
		SimpleIOHandler h = new SimpleIOHandler();
		model = h.convertFromOWL(getClass().getResourceAsStream("../../AR-TP53.owl"));
	}

	@Test
	public void testErToPE() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("urn:miriam:uniprot:P04637"), //TP53
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.erToPE(), 0, 1))));

		Assert.assertTrue(list.size() == 5);
	}

	@Test
	public void testPeToER() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_17220"), //p53
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.peToER(), 0, 1))));

		Assert.assertTrue(list.size() == 1);
	}

	@Test
	public void testDownControl() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35449"), //T-DHT/AR complex
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.downControl(), 0, 1))));

		Assert.assertTrue(list.size() == 1);
	}

	@Test
	public void testControlled() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35543"),
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.controlled(), 0, 1))));

		Assert.assertTrue(list.size() == 1);
		Assert.assertTrue(list.get(0).get(1) == model.getByID("http://pid.nci.nih.gov/biopaxpid_35537"));
	}

	@Test
	public void testControlToConv() throws Exception
	{
		// todo Test for nested controls

		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35543"),
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.controlToConv(), 0, 1))));

		Assert.assertTrue(list.size() == 1);
		Assert.assertTrue(list.get(0).get(1) == model.getByID("http://pid.nci.nih.gov/biopaxpid_35537"));
	}

	@Test
	public void testControlsConv() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35449"),
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.controlsConv(), 0, 1))));

		Assert.assertTrue(list.size() == 1);
		Assert.assertTrue(list.get(0).get(1) == model.getByID("http://pid.nci.nih.gov/biopaxpid_35537"));
	}

	@Test
	public void testGenericEquiv() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_637"), // GNAO1
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.genericEquiv(), 0, 1))));

		Assert.assertTrue(list.size() == 2);
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_623")));

		list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_623"), // Gi Family
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.genericEquiv(), 0, 1))));

		Assert.assertTrue(list.size() == 7);
	}

	@Test
	public void testComplexMembers() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35409"), // Gi family/GNB1/GNG2/GDP
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.complexMembers(), 0, 1))));

		Assert.assertTrue(list.size() == 4);
	}

	@Test
	public void testSimpleMembers() throws Exception
	{
		// todo Test for a nested complex

		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35409"), // Gi family/GNB1/GNG2/GDP
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.simpleMembers(), 0, 1))));

		Assert.assertTrue(list.size() == 4);
	}

	@Test
	public void testWithComplexMembers() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35409"), // Gi family/GNB1/GNG2/GDP
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.withComplexMembers(), 0, 1))));

		Assert.assertTrue(list.size() == 5);
	}

	@Test
	public void testWithSimpleMembers() throws Exception
	{
		// todo Test for a nested complex

		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35409"), // Gi family/GNB1/GNG2/GDP
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.withSimpleMembers(), 0, 1))));

		Assert.assertTrue(list.size() == 5);
	}

	@Test
	public void testComplexes() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_5511"), // GNB1
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.complexes(), 0, 1))));

		Assert.assertTrue(list.size() == 2);
	}

	@Test
	public void testWithComplexes() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_5511"), // GNB1
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.withComplexes(), 0, 1))));

		Assert.assertTrue(list.size() == 3);
	}

	@Test
	public void testLeft() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35537"),
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.left(), 0, 1))));

		Assert.assertTrue(list.size() == 2);
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_685")));
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_35409")));
	}

	@Test
	public void testRight() throws Exception
	{
		List<Match> list = Searcher.search(model.getByID("http://pid.nci.nih.gov/biopaxpid_35537"),
			new Pattern(2, Collections.singletonList(new MappedConst(ConBox.right(), 0, 1))));

		Assert.assertTrue(list.size() == 3);
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_678")));
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_21741")));
		Assert.assertTrue(collect(list, 1).contains(model.getByID("http://pid.nci.nih.gov/biopaxpid_31826")));
	}
	
	private Set<BioPAXElement> collect(List<Match> list, int index)
	{
		Set<BioPAXElement> set = new HashSet<BioPAXElement>();

		for (Match match : list)
		{
			set.add(match.get(index));
		}
		return set;
	}
}