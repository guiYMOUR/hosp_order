package com.example.hospital_se.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.hospital_se.dao.CommonUserDao;
import com.example.hospital_se.dao.OrderRecordsDao;
import com.example.hospital_se.entity.CommonUser;
import com.example.hospital_se.entity.OrderRecords;
import com.example.hospital_se.utils.MailUtil;


@Component
public class WorkerScheduler {

	@Autowired
	private OrderRecordsDao orderRecordsDao;
	@Autowired
	private CommonUserDao commonUserDao;
	@Autowired
	private MailUtil mailUtil;
	private static final Logger log = LoggerFactory.getLogger(WorkerScheduler.class);

	@Scheduled(cron = "0 0/2 * * * ?")
	public void createWorkTime() {
		// 查找需要发送通知的订单
		final List<OrderRecords> orderRecords = orderRecordsDao.findNeedNoticeOrder();
		// 循环订单
		for (int i = 0; i < orderRecords.size(); i++) {
			final CommonUser commonUser = commonUserDao.findCommonUserByUserId(orderRecords.get(i).getUserID());
			final OrderRecords orderRecordInfo = orderRecords.get(i);
			// 单独开启线程发送邮件，防止用户等待时间过长，成功日志输出，失败也输出。
			new Thread(new Runnable() {
				public void run() {
					boolean isSuccess = sendEmailCheck(commonUser, orderRecordInfo);
					if (isSuccess) {
						log.info(commonUser.getUserEmail() + "发送成功");

					} else {
						log.info(commonUser.getUserEmail() + "发送失败");
					}
				}
			}).start();
		}

	}

	public boolean sendEmailCheck(CommonUser commonUser, OrderRecords orderRecords) {
		String contentInfo = commonUser.getUserName() + "您好：" + "\n<br>您预约的" + orderRecords.getHospitalName() + "-"
				+ orderRecords.getOfficesName() + "-" + orderRecords.getDoctorName() + "医生的订单已成功通过审核" + "\n<br>请您持有效证件于"
				+ orderRecords.getTransactDate() + "-" + orderRecords.getTransactTime() + "之前前往！";
		String email = commonUser.getUserEmail();
		log.info(email);
		String sender = "天津市医院预约系统";
		String title = "天津市医院预约挂号【预约通知提醒】";
		String content = contentInfo + "\n<br>天津市预约挂号系统。（项目测试）";
		boolean isSuccess = mailUtil.sendMail(email, sender, title, content);
		if (isSuccess) {
			log.info("发送成功");
			// commonUserDao.sendVerification(userEmail, verificationCode,
			// updateTime);
			orderRecordsDao.updateSendSuccess(orderRecords.getId());
			return true;
		} else {
			log.info("发送失败");
			orderRecordsDao.updateSendFailed(orderRecords.getId());
			return false;
		}
	}
}
