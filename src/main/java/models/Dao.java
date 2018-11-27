package models;

import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component
public class Dao {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private static final String postGreQuery1 = "SELECT A.ssoid, A.formid From public.test_case as A Where ( SELECT max(B.ts) From public.test_case as B ) - A.ts < '1 hours'";
    private static final String namedQuery2 = "SELECT t.ssoid, t.subtype from CsvRow t where (t.subtype!='send' and t.ssoid!='Unauthorized')";
    private static final String namedQuery3 = "SELECT distinct t.formid, count (t.formid) FROM CsvRow t group by (t.formid)";

    public Dao()
    {}

    public Dao(SessionFactory factory)
    {
        sessionFactory = factory;
    }

    public <T> T save(final T o) {
        return (T) sessionFactory.getCurrentSession().save(o);
    }

    public void delete(final Object object) {
        sessionFactory.getCurrentSession().delete(object);
    }

    /***/
    public <T> T get(final Class<T> type, final Long id) {
        return (T) sessionFactory.getCurrentSession().get(type, id);
    }

    /***/
    public <T> T merge(final T o) {
        return (T) sessionFactory.getCurrentSession().merge(o);
    }

    /***/
    public <T> void saveOrUpdate(final T o) {
        sessionFactory.getCurrentSession().saveOrUpdate(o);
    }

    public <T> T first(final Class<T> type) {
        List<T> list = null;
        final Session session = sessionFactory.getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            final Criteria crit = session.createCriteria(type);
            crit.setFirstResult(0);
            crit.setMaxResults(1);
            list = crit.list();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        }
        finally {
            if (tr != null)
                tr.commit();
        }
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public <T> List<T> getAll(final Class<T> type) {
        final Session session = sessionFactory.getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            final Criteria crit = session.createCriteria(type);
            return crit.list();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        }
        finally {
            if (tr != null)
                tr.commit();
        }
        return Collections.emptyList();
    }


    public <T> List<T> getPostgreQuery(final Class<T> type) {
        final Session session = sessionFactory.getCurrentSession();

        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query<T> q = session.createSQLQuery(Dao.postGreQuery1);
            return q.list();

        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        }
        finally {
            if (tr != null)
                tr.commit();
        }
        return Collections.emptyList();
    }

    public <T> List<T> getNamedQuery2(final Class<T> type) {
        final Session session = sessionFactory.getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query q = session.createQuery(Dao.namedQuery2);
            q.setMaxResults(50);
            // https://www.baeldung.com/hibernate-pagination
            /*
            int pageSize = 10;
            ScrollableResults resultScroll = q.scroll(ScrollMode.FORWARD_ONLY);
            resultScroll.first();
            resultScroll.scroll(0);
            List<Foo> fooPage = Lists.newArrayList();
            int i = 0;
            while (pageSize > i++) {
                fooPage.add((Foo) resultScroll.get(0));
                if (!resultScroll.next())
                    break;
            }
            */
            return q.getResultList();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        }
        finally {
            if (tr != null)
                tr.commit();
        }
        return Collections.emptyList();
    }


    public <T> List<T> getNamedQuery3(final Class<T> type) {
        final Session session = sessionFactory.getCurrentSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            Query q = session.createQuery(Dao.namedQuery3);
            q.setMaxResults(5); // По условию 5 топовых
            return q.getResultList();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
                tr = null;
            }
            e.printStackTrace();
        }
        finally {
            if (tr != null)
                tr.commit();
        }
        return Collections.emptyList();
    }

}