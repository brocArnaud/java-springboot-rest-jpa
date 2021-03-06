/*
 * Created on 2017-12-20 ( Date ISO 2017-12-20 - Time 14:58:55 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
*/
package org.demo.service;
import org.demo.entity.BookOrderItem;
import org.demo.entity.BookOrderItemKey;
import org.demo.repository.BookOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service implementation for BookOrderItem.
 * @author Telosys (http://www.telosys.org/) version 3.0.0
 */
@Service
public class BookOrderItemServiceImpl implements BookOrderItemService {

	@Autowired
	private BookOrderItemRepository bookOrderItemRepository;

	@Override
	public Page<BookOrderItem> search(Pageable pageable) {
		return bookOrderItemRepository.findAll(pageable);
	}

	@Override
	public BookOrderItem get(Integer bookOrderId, Integer bookId) {
		// Build the composite key
		BookOrderItemKey key = new BookOrderItemKey(bookOrderId, bookId);
		return bookOrderItemRepository.findOne(key);
	}

	@Override
	public Boolean delete(Integer bookOrderId, Integer bookId) {
		// Build the composite key
		BookOrderItemKey key = new BookOrderItemKey(bookOrderId, bookId);
		if (bookOrderItemRepository.findOne(key) != null) {
			bookOrderItemRepository.delete(key);
			return true;
		}
		return false;
	}

	@Override
	public BookOrderItem create(BookOrderItem bookOrderItem) {
		return bookOrderItemRepository.save(bookOrderItem);
	}

	@Override
	public Boolean save(BookOrderItem bookOrderItem) {
		// Build the composite key
		BookOrderItemKey pk = new BookOrderItemKey(bookOrderItem.getBookOrderId(), bookOrderItem.getBookId());
		if (bookOrderItemRepository.findOne(pk) != null) {
			bookOrderItemRepository.save(bookOrderItem);
			return true;
		}
		return false;
	}

	@Override
	public Boolean exist(BookOrderItem bookOrderItem) {
		// Build the composite key
		BookOrderItemKey pk = new BookOrderItemKey(bookOrderItem.getBookOrderId(), bookOrderItem.getBookId());
		return bookOrderItemRepository.exists(pk);
	}


}
