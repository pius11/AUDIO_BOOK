package com.dimata.demo.audiobook.demo_audio_book.core.api;

/**
 * Convert form to record.
 *
 * @param <R> Record
 */
public interface RecordAdapter<R> {
	/**
	 * Convert to record which this record will be used
	 * to create new record.
	 * 
	 * @return Record object to be used as new record.
	 */
	R convertNewRecord();
	/**
	 * Convert to record
	 * 
	 * @return Record.
	 */
	R convertToRecord();
}
