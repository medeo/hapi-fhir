package ca.uhn.fhir.jpa.entity;

/*
 * #%L
 * HAPI FHIR JPA Server
 * %%
 * Copyright (C) 2014 - 2019 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import ca.uhn.fhir.util.ValidateUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.io.Serializable;

import static org.apache.commons.lang3.StringUtils.left;
import static org.apache.commons.lang3.StringUtils.length;

@Table(name = "TRM_VALUESET_C_DESIGNATION", indexes = {
	@Index(name = "IDX_VALUESET_C_DSGNTN_VAL", columnList = "VAL")
})
@Entity()
public class TermValueSetConceptDesignation implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int MAX_LENGTH = 500;

	@Id()
	@SequenceGenerator(name = "SEQ_VALUESET_C_DSGNTN_PID", sequenceName = "SEQ_VALUESET_C_DSGNTN_PID")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_VALUESET_C_DSGNTN_PID")
	@Column(name = "PID")
	private Long myId;

	@ManyToOne()
	@JoinColumn(name = "VALUESET_CONCEPT_PID", referencedColumnName = "PID", nullable = false, foreignKey = @ForeignKey(name = "FK_TRM_VALUESET_CONCEPT_PID"))
	private TermValueSetConcept myConcept;

	@Column(name = "LANG", nullable = true, length = MAX_LENGTH)
	private String myLanguage;

	@Column(name = "USE_SYSTEM", nullable = true, length = MAX_LENGTH)
	private String myUseSystem;

	@Column(name = "USE_CODE", nullable = true, length = MAX_LENGTH)
	private String myUseCode;

	@Column(name = "USE_DISPLAY", nullable = true, length = MAX_LENGTH)
	private String myUseDisplay;

	@Column(name = "VAL", nullable = false, length = MAX_LENGTH)
	private String myValue;

	public Long getId() {
		return myId;
	}

	public TermValueSetConcept getConcept() {
		return myConcept;
	}

	public TermValueSetConceptDesignation setConcept(TermValueSetConcept theConcept) {
		myConcept = theConcept;
		return this;
	}

	public String getLanguage() {
		return myLanguage;
	}

	public TermValueSetConceptDesignation setLanguage(String theLanguage) {
		ValidateUtil.isNotTooLongOrThrowIllegalArgument(theLanguage, MAX_LENGTH,
			"Language exceeds maximum length (" + MAX_LENGTH + "): " + length(theLanguage));
		myLanguage = theLanguage;
		return this;
	}

	public String getUseSystem() {
		return myUseSystem;
	}

	public TermValueSetConceptDesignation setUseSystem(String theUseSystem) {
		ValidateUtil.isNotTooLongOrThrowIllegalArgument(theUseSystem, MAX_LENGTH,
			"Use system exceeds maximum length (" + MAX_LENGTH + "): " + length(theUseSystem));
		myUseSystem = theUseSystem;
		return this;
	}

	public String getUseCode() {
		return myUseCode;
	}

	public TermValueSetConceptDesignation setUseCode(String theUseCode) {
		ValidateUtil.isNotTooLongOrThrowIllegalArgument(theUseCode, MAX_LENGTH,
			"Use code exceeds maximum length (" + MAX_LENGTH + "): " + length(theUseCode));
		myUseCode = theUseCode;
		return this;
	}

	public String getUseDisplay() {
		return myUseDisplay;
	}

	public TermValueSetConceptDesignation setUseDisplay(String theUseDisplay) {
		myUseDisplay = left(theUseDisplay, MAX_LENGTH);
		return this;
	}

	public String getValue() {
		return myValue;
	}

	public TermValueSetConceptDesignation setValue(@Nonnull String theValue) {
		ValidateUtil.isNotBlankOrThrowIllegalArgument(theValue, "theValue must not be null or empty");
		ValidateUtil.isNotTooLongOrThrowIllegalArgument(theValue, MAX_LENGTH,
			"Value exceeds maximum length (" + MAX_LENGTH + "): " + length(theValue));
		myValue = theValue;
		return this;
	}

	@Override
	public boolean equals(Object theO) {
		if (this == theO) return true;

		if (!(theO instanceof TermValueSetConceptDesignation)) return false;

		TermValueSetConceptDesignation that = (TermValueSetConceptDesignation) theO;

		return new EqualsBuilder()
			.append(getLanguage(), that.getLanguage())
			.append(getUseSystem(), that.getUseSystem())
			.append(getUseCode(), that.getUseCode())
			.append(getUseDisplay(), that.getUseDisplay())
			.append(getValue(), that.getValue())
			.isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
			.append(getLanguage())
			.append(getUseSystem())
			.append(getUseCode())
			.append(getUseDisplay())
			.append(getValue())
			.toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
			.append("myId", myId)
			.append(myConcept != null ? ("myConcept - id=" + myConcept.getId()) : ("myConcept=(null)"))
			.append("myLanguage", myLanguage)
			.append("myUseSystem", myUseSystem)
			.append("myUseCode", myUseCode)
			.append("myUseDisplay", myUseDisplay)
			.append("myValue", myValue)
			.toString();
	}
}
