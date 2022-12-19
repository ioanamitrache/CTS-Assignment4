package mitrache.ioana.g1092.assignment4.tests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@SuiteClasses({ TestPayOnlineAccount.class, TestSetIBANAccount.class })
@IncludeCategory({CategoryImportant.class})
public class ImportantTests {

}
