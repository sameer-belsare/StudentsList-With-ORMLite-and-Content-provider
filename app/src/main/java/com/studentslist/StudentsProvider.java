package com.studentslist;

import com.tojc.ormlite.android.OrmLiteSimpleContentProvider;
import com.tojc.ormlite.android.framework.MatcherController;
import com.tojc.ormlite.android.framework.MimeTypeVnd;

/**
 * Created by sameer.belsare on 9/2/17.
 */

public class StudentsProvider extends OrmLiteSimpleContentProvider<StudentsHelper> {
    @Override
    protected Class<StudentsHelper> getHelperClass() {
        return StudentsHelper.class;
    }

    @Override
    public boolean onCreate() {
        setMatcherController(new MatcherController()
                .add(Student.class, MimeTypeVnd.SubType.DIRECTORY, "", StudentContract.CONTENT_URI_PATTERN_MANY)
                .add(Student.class, MimeTypeVnd.SubType.ITEM, "#", StudentContract.CONTENT_URI_PATTERN_ONE)
        );
        return true;
    }
}
