package org.colorcoding.tools.btulz.transformers.regions;

import java.util.ArrayList;
import java.util.Iterator;

import org.colorcoding.tools.btulz.models.IModel;
import org.colorcoding.tools.btulz.models.IProperty;
import org.colorcoding.tools.btulz.models.data.emYesNo;
import org.colorcoding.tools.btulz.templates.Parameter;

/**
 * 区域-模型主键
 * 
 * @author Niuren.Zhu
 *
 */
public class RegionModelPrimaryProperty extends RegionBase {
	/**
	 * 此区域标记
	 */
	public static final String REGION_DELIMITER = "MODEL_PRIMARY_PROPERTY";

	public RegionModelPrimaryProperty() {
		super(REGION_DELIMITER);
	}

	@Override
	protected Iterable<Parameter> getRegionParameters() {
		Parameter parameter = this.getParameter(RegionModel.REGION_DELIMITER);
		if (parameter != null) {
			if (parameter.getValue() instanceof IModel) {
				IModel model = (IModel) parameter.getValue();
				ArrayList<IProperty> primaryProperty = new ArrayList<>();
				for (IProperty iProperty : model.getProperties()) {
					if (iProperty.isPrimaryKey() == emYesNo.Yes) {
						primaryProperty.add(iProperty);
					}
				}
				return new Iterable<Parameter>() {
					@Override
					public Iterator<Parameter> iterator() {

						return new Iterator<Parameter>() {
							int curIndex = 0;

							@Override
							public boolean hasNext() {
								return curIndex < primaryProperty.size() ? true : false;
							}

							@Override
							public Parameter next() {
								Parameter parameter = new Parameter();
								parameter.setName(REGION_DELIMITER);
								parameter.setValue(primaryProperty.get(curIndex));
								curIndex++;
								return parameter;
							}
						};
					}

				};
			}
		}
		return null;
	}

}
