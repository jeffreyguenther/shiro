package org.shirolang.playground;

public class StyleInfo {
	private int start;
	private int end;
	private String style;
	
	public StyleInfo(int start, int end, String style) {
		this.start = start;
		this.end = end;
		this.style = style;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + end;
		result = prime * result + start;
		result = prime * result + ((style == null) ? 0 : style.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StyleInfo other = (StyleInfo) obj;
		if (end != other.end) {
			return false;
		}
		if (start != other.start) {
			return false;
		}
		if (style == null) {
			if (other.style != null) {
				return false;
			}
		} else if (!style.equals(other.style)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StyleInfo [start=").append(start).append(", end=")
				.append(end).append(", ");
		if (style != null)
			builder.append("style=").append(style);
		builder.append("]");
		return builder.toString();
	}
}
