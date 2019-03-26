package cn.uestc.service.impl;

import cn.uestc.pojo.Account;
import cn.uestc.pojo.AccountResult;
import cn.uestc.pojo.Log;
import cn.uestc.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * 关于转账账号，密码为空或者是空字符串的判断应该放在前端，这里放在后端判断了，但是显然
 * 这样会给后端服务器造成压力冗余，只是练习这么写。
 *
 *
 * PS:如果判断账户是否存在 select count（1）from account where  name=？的效率是比select * 的效率高的
 * 然是如果我们需要查询出账户的其他信息，不仅仅判断存在，还是需要些select *　同时记住，最好不要写select *
 * 这里是练习数据量少这么写，查什么数据select什么数据，最好不要做全表查询和全字段查询，这样可以提高数据库
 * 服务器处理效率，提高并发
 *
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountResult transferAccount(String payAccount, String password, String money, String gainAccount, String name) throws IOException {
        AccountResult result = new AccountResult();

        if (payAccount == null || password==null||payAccount.trim().equalsIgnoreCase("")||password.trim().equalsIgnoreCase("")) {
            result.setState(AccountResult.ERROR);
            result.setMessage("请输入账户名称与密码");
            return result;
        }

        if(money==null||money.trim().equalsIgnoreCase("")){
            result.setMessage("请输入汇款金额");
            result.setState(AccountResult.ERROR);
            return result;
        }
        InputStream resource = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //转账账户
        Account pay = sqlSession.selectOne("cn.uestc.mapper.AccountMapper.selByAccno", payAccount);
        if (pay == null) {
            result.setMessage("转账账户不存在");
            result.setState(AccountResult.ERROR);
            return result;
        }
        if (!password.trim().equalsIgnoreCase(pay.getPassword().toString())) {
            result.setMessage("转账账户密码错误");
            result.setState(AccountResult.ERROR);
            return result;
        }
        //将转账金额转成Double
        Double remittance = Double.parseDouble(money);
        Double balance = pay.getBalance();
        if (remittance > balance) {
            result.setMessage("余额不足");
            result.setState(AccountResult.ERROR);
            return result;
        }
        //收款账户
        Account gain = sqlSession.selectOne("cn.uestc.mapper.AccountMapper.selByAccno", gainAccount);
        if (gain == null) {
            result.setState(AccountResult.ERROR);
            result.setMessage("收款账户不存在");
            return result;
        }
        if (!name.trim().equalsIgnoreCase(gain.getName())) {
            result.setState(AccountResult.ERROR);
            result.setMessage("收款账户与收款人姓名不匹配");
            return result;
        }

        try {
            pay.setBalance(pay.getBalance()-remittance);
            //更新汇款账户
            int update1 = sqlSession.update("cn.uestc.mapper.AccountMapper.upd", pay);
            //更新收款账户
            gain.setBalance(gain.getBalance()+remittance);
            int update2 = sqlSession.update("cn.uestc.mapper.AccountMapper.upd", gain);
            if ((update1 + update2) == 2) {
                Log log = new Log();
                log.setAcconOut(pay.getAccno());
                log.setAcconIn(gain.getAccno());
                log.setBalance(remittance);
                sqlSession.insert("cn.uestc.mapper.logMapper.insertLog",log);
            }
            sqlSession.commit();
            Logger logger = Logger.getLogger(AccountService.class);
            logger.info(pay.getAccno()+"给"+gain.getAccno()+"转了"+remittance+"元");
        }catch (Exception e){
            result.setState(AccountResult.ERROR);
            result.setMessage("服务器异常");
            Logger logger = Logger.getLogger(AccountService.class);
            logger.error(e.getMessage());
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
        result.setState(AccountResult.SUCCESS);
        result.setMessage("转账成功");
        return result;
    }
}
