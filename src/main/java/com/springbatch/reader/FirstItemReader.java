package com.springbatch.reader;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class FirstItemReader implements ItemReader<Integer> {

	List<Integer> itemList = Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10);
	int i = 0;
	@Override
	public Integer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		Integer item;
		System.out.println("iam in FirstItemReader");
		if (i < itemList.size()) {
			item = itemList.get(i);
			i++;
			return item;
		}

		return null;
	}

}
