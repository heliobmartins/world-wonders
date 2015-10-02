package br.com.ciandt.helio.worldwonders.listener;

import br.com.ciandt.helio.worldwonders.entity.vo.OperationError;

public abstract class OperationListener<TResult> {

	/**
	 * Operation Success
	 * 
	 * @param result - TResult
	 */
	public abstract void onOperationSuccess(final TResult result);
	
	/**
	 * Operation Success with Error
	 *
	 * @param error - OperationError
	 */
	public abstract void onOperationError(final OperationError error);

	/**
	 * Operation Cancelled
	 */
	public void onOperationCancelled() {

	}

}
