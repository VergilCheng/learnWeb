package cn.uestc.service;

import cn.uestc.pojo.Account;
import cn.uestc.pojo.AccountResult;

import java.io.IOException;

public interface AccountService {

    AccountResult transferAccount(String payAccount, String password, String money, String gainAccount, String name) throws IOException;

}
