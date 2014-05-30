/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shiro.functions.data;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 *
 * @author jeffreyguenther
 */
@RunWith(Suite.class)
@SuiteClasses({CSV2TableMFuncTest.class, ColumnAverageMFuncTest.class, 
    DateColumnConverterMFuncTest.class, FilterTableMFuncTest.class,
    IntersectMFuncTest.class, SelectColumnMFuncTest.class,
    SortTableMFuncTest.class, UnionMFuncTest.class
})
public class DataMFTests {
}
