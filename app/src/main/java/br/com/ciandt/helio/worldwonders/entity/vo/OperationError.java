package br.com.ciandt.helio.worldwonders.entity.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class OperationError implements Parcelable {

	private String errorCode;
	private String errorMessage;

	/**
	 * Default Constructor
	 */
	public OperationError() {
	}

	/**
	 * Default Constructor with parameters
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	public OperationError(final String errorCode, final String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(this.errorCode);
		out.writeString(this.errorMessage);
	}

	public static final Creator<OperationError> CREATOR = new Creator<OperationError>() {
		public OperationError createFromParcel(Parcel in) {

			OperationError operationError = new OperationError();
			operationError.errorCode = in.readString();
			operationError.errorMessage = in.readString();

			return operationError;
		}

		public OperationError[] newArray(int size) {
			return new OperationError[size];
		}
	};
}
