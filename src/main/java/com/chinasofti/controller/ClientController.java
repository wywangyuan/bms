package com.chinasofti.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.bms.View.BookView;
import com.chinasofti.bms.View.MainView;
import com.chinasofti.bms.View.ManagerView;
import com.chinasofti.bms.View.ReaderView;
import com.chinasofti.bms.domain.Book;
import com.chinasofti.bms.domain.BorrowBook;
import com.chinasofti.bms.domain.Reader;
import com.chinasofti.bms.domain.ReaderType;
import com.chinasofti.bms.domain.User;
import com.chinasofti.bms.service.TotalService;
import com.chinasofti.bms.util.DateUtil;
import com.chinasofti.bms.util.UserInput;

//bms客户端
public class ClientController {
	private BookView bv;
	private MainView mv;
	private ManagerView nv;
	private ReaderView rv;
	private int select;

	private UserInput ui;

	public static final String IP = "10.10.49.112";
	public static final int PORT = 12345;
	private TotalService totalService;

	public ClientController() {
		this.bv = new BookView();
		this.mv = new MainView();
		this.nv = new ManagerView();
		this.rv = new ReaderView();
		this.ui = new UserInput();
		totalService = ClientProxy.getClient(TotalService.class, IP, PORT);
	}

	public void start() {
		while (true) {
			this.mv.homeView();// 用户登录界面
			mv.userView();
			select = this.ui.getInt("请选择：");
			if (select == 0) {
				System.out.println("系统退出，欢迎再次使用！");
				System.exit(0);
			} else if (select == 1) {// 管理员登录
				managerLogin();
			} else if (select == 2) {// 读者登录
				readLogin();
			} else {
				System.out.println("输入有误，请重新输入");
			}
		}
	}

	private void readLogin() {
		Reader reader = readerLogin();
		if (reader != null) {
			System.out.println("尊敬的读者" + reader.getRname() + "，欢迎您！");
			while (true) {
				rv.welcome();
				select = this.ui.getInt("请选择：");
				if (select == 1) {// 查询图书信息
					selectBook();
				} else if (select == 2) {// 借阅图书
					rv.book();
					select = this.ui.getInt("请选择：");
					if (select == 1) {// 按图书类型借书
						int typeId = this.ui.getInt("请输入图书类型id:");
						List<Book> books = totalService
								.getBooksByTypeId(typeId);
						borrowBook(reader, books);
					} else if (select == 2) {// 安图书名称借书
						String name = this.ui.getString("请输入书名:");
						List<Book> books = totalService.getBooksByName(name);
						borrowBook(reader, books);
					} else if (select == 3) {// 所有图书
						List<Book> books = totalService.getAllBooks();
						borrowBook(reader, books);
					} else if (select == -1) {
						System.out.println("返回上一层");
						break;
					}
				} else if (select == 3) {// 归还图书
					returnBook(reader);
				} else if (select == 4) {// 修改个人登录密码
					loginPassword(reader);
				} else if (select == 5) {
					selectAccount(reader);
				} else if (select == -1) {
					System.out.println("返回上一层");
					break;
				}
			}
		} else {
			System.out.println("用户名或密码输入有误!");
		}

	}

	private void selectAccount(Reader reader) {
		System.out.println("用户名:" + reader.getRname());
		System.out.println("账户余额为:" + reader.getMoney());
		List<BorrowBook> borrowBooks = reader.getBorrowBook();
		List<Book> books = new ArrayList<>();
		for (BorrowBook borrowBook : borrowBooks) {
			Book book = totalService.getBooksById(borrowBook.getBid());
			books.add(book);
		}
		System.out.println("借阅信息:");
		System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
		for (Book book : books) {
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());
		}
	}

	private void returnBook(Reader reader) {// 归还图书
		List<BorrowBook> borrowBooks = reader.getBorrowBook();
		List<Book> books = new ArrayList<>();
		for (BorrowBook borrowBook : borrowBooks) {
			Book book = totalService.getBooksById(borrowBook.getBid());
			books.add(book);
		}
		System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
		for (Book book : books) {
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());
		}
		int bid = this.ui.getInt("请输入要归还的图书id");
		BorrowBook borrowBook = totalService.getBorrowBookById(reader.getRid(),
				bid);
		Date startDate = borrowBook.getBorrowdate();
		Date endDate = DateUtil.getCurrentDate();
		borrowBook.setReturndate(endDate);
		int day = DateUtil.getDay(startDate, endDate);
		ReaderType readType = reader.getReadType();
		Integer limitday = readType.getLimitday();
		Book book = totalService.getBooksById(borrowBook.getBid());
		if (day > limitday) {
			double money = 10.0 * (day - limitday);
			if (money > book.getPrice()) {
				boolean flag = totalService.returnBook(borrowBook);
				if (flag) {
					totalService.updateReader(reader);
					System.out.println("还书成功!");
					System.out.println("因书逾期扣除押金:" + book.getPrice() + "元");
				} else {
					System.out.println("还书失败!");
				}
			} else {
				reader.setMoney(reader.getMoney() + (book.getPrice() - money));
				boolean flag = totalService.returnBook(borrowBook);
				if (flag) {
					totalService.updateReader(reader);
					System.out.println("还书成功!");
					System.out.println("因书逾期扣除押金:" + money + "元");
				} else {
					System.out.println("还书失败!");
				}
			}
		} else {
			boolean flag = totalService.returnBook(borrowBook);
			if (flag) {
				reader.setMoney(reader.getMoney() + book.getPrice());
				totalService.updateReader(reader);
				System.out.println("还书成功!");
			} else {
				System.out.println("还书失败!");
			}
		}
	}

	private void borrowBook(Reader reader, List<Book> books) {// 借阅图书
		System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
		for (Book book : books) {
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());
		}
		int bid = this.ui.getInt("请输入要借阅的图书编号:");
		Book book = totalService.getBooksById(bid);
		if (book.getBid() == bid) {
			if (reader.getMoney() > book.getPrice()) {
				reader.setMoney(reader.getMoney() - book.getPrice());
				BorrowBook borrowBook = new BorrowBook();
				borrowBook.setBid(bid);
				borrowBook.setRid(reader.getRid());
				borrowBook.setBorrowdate(DateUtil.getCurrentDate());
				List<BorrowBook> borrowBooks = new ArrayList<BorrowBook>();
				borrowBooks.add(borrowBook);
				reader.setBorrowBook(borrowBooks);
				boolean flag = totalService.borrowBook(reader);
				if (flag) {
					System.out.println("借阅成功!");
				} else {
					System.out.println("借阅失败!");
				}
			} else {
				System.out.println("卡上余额不足!");
			}
		}
	}

	// 管理员登录
	private void managerLogin() {
		int id = this.ui.getInt("请输入账号：");
		String pwd = this.ui.getString("请输入密码");
		User user = totalService.userLogin(id, pwd);
		if (user != null) {
			if (user.getUname().equals("admin")) {// admin管理员登录
				System.out.println("尊敬的超级管理员" + user.getUname() + "，欢迎您！");
				while (true) {
					nv.aView();
					select = this.ui.getInt("请选择：");
					if (select == 1) {// 图书信息管理
						// 增删改查视图
						ManagerBook();
					} else if (select == 2) {// 读者信息管理
						// 读者增删改查视图
						ManagerReader();
					} else if (select == 3) {// 管理员信息管理
						// 管理员增删改查视图
						while (true) {
							nv.vView();
							select = this.ui.getInt("请选择：");
							if (select == 1) {// 查询管理员信息
								getAllUser();
							} else if (select == 2) {// 添加管理员
								addUser();
							} else if (select == 3) {// 修改管理员
								updateUser();
							} else if (select == 4) {// 删除管理员
								deleteUser();
							} else if (select == -1) {//
								System.out.println("返回上一层");
								break;
							}
						}
					} else if (select == 4) {
						int rid = this.ui.getInt("请输入要充值的读者编号:");
						Reader reader = totalService.getReaderById(rid);
						while (true) {
							if (reader != null) {
								double money = this.ui.getDouble("请输入要充值的金额");
								reader.setMoney(reader.getMoney() + money);
								totalService.updateReader(reader);
								System.out.println("充值成功!");
								break;
							} else {
								System.out.println("您查询的读者不存在,请重新输入!");
							}
						}
					} else if (select == 0) {// 退出系统
						System.out.println("系统退出，欢迎再次使用");
						System.exit(0);
					} else if (select == -1) {
						break;
					} else {
						System.out.println("输入有误请重新输入");
					}
				}

			} else {// 管理员登录
				System.out.println("尊敬的管理员" + user.getUname() + "欢迎您！");
				nv.mView();
				select = this.ui.getInt("请选择：");
				if (select == 1) {// 图书信息管理
					ManagerBook();
				} else if (select == 2) {// 读者信息管理
					ManagerReader();
				} else if (select == 0) {
					System.out.println("系统退出");
				}
			}
		} else {
			System.out.println("登录失败，请重新登录！");
		}
	}

	private void ManagerBook() {// 管理图书
		while (true) {
			bv.bView();
			select = this.ui.getInt("请选择：");
			if (select == 1) {
				selectBook();
			} else if (select == 2) {// 添加图书
				addBook();
			} else if (select == 3) {// 修改图书信息
				updateBook();
			} else if (select == 4) {// 删除图书
				deleteBook();
			} else if (select == -1) {// 返回上一层
				break;
			} else if (select == 0) {
				System.out.println("系统退出！");
			}
		}
	}

	// 添加图书
	public void addBook() {
		System.out.println("**********图书信息录入**********");
		System.out.println("----------------------------");
		// int bid = this.ui.getInt("请输入图书编号：");
		// Boolean id = this.totalService.addBook(bid);
		// 查询书的id是否存在
		Book book = new Book();
		// Book b = this.totalService.getBooksById(book.getBid());
		String bname = this.ui.getString("请输入书名:");
		String author = this.ui.getString("请输入图书作者：");
		String publish = this.ui.getString("请输入图书出版社：");
		int number = this.ui.getInt("请输入要存入的图书数量：");
		double price = this.ui.getDouble("请输入图书价格：");
		int btid = this.ui.getInt("请输入图书类型编号：");
		book.setBname(bname);
		book.setAuthor(author);
		book.setBnumber(number);
		book.setPublish(publish);
		book.setPrice(price);
		book.setBtid(btid);
		Boolean flag = totalService.addBook(book);
		if (flag) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	// 删除图书
	private void deleteBook() {
		System.out.println("-------删除图书---------");
		int bid = this.ui.getInt("请输入要删除的书籍的编号:");
		Book book = this.totalService.getBooksById(bid);
		if (book == null) {
			System.out.println("该书不存在");
		} else {
			System.out.println("图书信息如下:");
			System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());

			// System.out.println(b.toString());
			if ("y".equals(this.ui.getString("是否确认删除?(y/n):"))) {
				Boolean flag = totalService.deleteBook(bid);
				if (flag) {
					System.out.println("删除成功！");
				} else {
					System.out.println("删除失败！");
				}
			} else {
				System.out.println("删除取消");
			}
		}
	}

	// 修改图书信息
	private void updateBook() {
		System.out.println("-------修改图书---------");
		int bid = this.ui.getInt("请输入要修改的书籍的编号:");
		Book book = this.totalService.getBooksById(bid);
		if (book == null) {
			System.out.println("该书不存在");
		} else {
			String bname = this.ui.getString("请输入书名:");
			String author = this.ui.getString("请输入图书作者：");
			String publish = this.ui.getString("请输入图书出版社：");
			int number = this.ui.getInt("请输入要存入的图书数量：");
			double price = this.ui.getDouble("请输入图书价格：");
			int btid = this.ui.getInt("请输入图书类型编号：");
			book.setBname(bname);
			book.setAuthor(author);
			book.setBnumber(number);
			book.setPublish(publish);
			book.setPrice(price);
			book.setBtid(btid);
			Boolean flag = totalService.updateBook(book);
			if (flag) {
				System.out.println("修改成功!");
			} else {
				System.out.println("修改失败!");
			}
		}
	}

	private void ManagerReader() {// 管理读者
		while (true) {
			nv.readerView();
			select = this.ui.getInt("请选择：");
			if (select == 1) {// 查询读者信息
				getAllReader();
			} else if (select == 2) {// 添加读者
				addReader();
			} else if (select == 3) {// 修改读者
				updateReader();
			} else if (select == 4) {// 删除读者
				deleteReader();
			} else if (select == -1) {// 返回上一层
				break;
			}
		}
	}

	// 查询读者
	private void getAllReader() {
		List<Reader> readers = this.totalService.getAllReader();
		if (readers == null) {
			System.out.println("没有此读者");
		} else {

			System.out
					.println("读者编号\t\t读者类型编号\t读者姓名\t\t读者年龄\t\t读者性别\t\t读者电话\t\t所在系部");
			for (Reader reader : readers) {
				// System.out.println(reader);
				System.out.println(reader.getRid() + "\t" + reader.getTid()
						+ "\t" + reader.getRname() + "\t" + reader.getAge()
						+ "\t" + reader.getSex() + "\t" + reader.getPhone()
						+ "\t" + reader.getDept());

			}
		}
	}

	// 添加读者
	private void addReader() {
		System.out.println("**********读者信息录入**********");
		System.out.println("----------------------------");
		Reader reader = new Reader();
		String rname = this.ui.getString("请输入读者姓名:");
		int age = this.ui.getInt("请输入读者年龄:");
		String sex = this.ui.getString("请输入读者性别:");
		String phone = this.ui.getString("请输入读者电话:");
		String dept = this.ui.getString("请输入读者所在系部::");
		String password = this.ui.getString("请输入登录密码:");
		int tid = this.ui.getInt("请输入读者类型编号:");
		reader.setRname(rname);
		reader.setAge(age);
		reader.setSex(sex);
		reader.setPhone(phone);
		reader.setDept(dept);
		reader.setTid(tid);
		reader.setPassword(password);
		reader.setMoney(100.0);
		Boolean flag = totalService.addReader(reader);
		if (flag) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	// 修改读者信息
	private void updateReader() {
		System.out.println("-------修改读者信息---------");
		int rid = this.ui.getInt("请输入要修改的读者的编号:");
		Reader reader = this.totalService.getReaderById(rid);
		if (reader == null) {
			System.out.println("没有此读者");
		} else {
			String rname = this.ui.getString("请输入读者姓名:");
			int age = this.ui.getInt("请输入读者年龄:");
			String sex = this.ui.getString("请输入读者性别:");
			String phone = this.ui.getString("请输入读者电话:");
			String dept = this.ui.getString("请输入读者所在系部::");
			int tid = this.ui.getInt("请输入读者类型编号:");
			reader.setRname(rname);
			reader.setAge(age);
			reader.setSex(sex);
			reader.setPhone(phone);
			reader.setDept(dept);
			reader.setTid(tid);
			boolean flag = this.totalService.updateReader(reader);
			if (flag) {
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！");
			}
		}
	}

	// 删除读者
	private void deleteReader() {
		System.out.println("-------删除读者---------");
		int rid = this.ui.getInt("请输入要删除的读者的编号:");
		Reader reader = this.totalService.getReaderById(rid);
		if (reader == null) {
			System.out.println("读者不存在");
		}
		System.out.println("读者信息如下:");
		// System.out.println(r.toString());
		System.out
				.println("读者编号\t\t读者类型编号\t读者姓名\t\t读者年龄\t\t读者性别\t\t读者电话\t\t所在系部");
		System.out.println(reader.getRid() + "\t\t" + reader.getTid() + "\t\t"
				+ reader.getRname() + "\t\t" + reader.getAge() + "\t"
				+ reader.getSex() + "\t\t" + reader.getPhone() + "\t\t"
				+ reader.getDept());

		if ("y".equals(this.ui.getString("是否确认删除?(y/n):"))) {
			boolean flag1 = this.totalService.deleteReader(rid);
			if (flag1) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
		} else {
			System.out.println("删除取消");
		}
	}

	private void selectBook() {
		// 四种查询图书的方法
		while (true) {
			bv.selectView();
			select = this.ui.getInt("请选择：");
			if (select == 4) {// 查询所有图书
				this.getAllBooks();
			} else if (select == 3) {// 根据书名查书
				this.getBooksByName();
			} else if (select == 2) {// 根据书类型编号查书
				this.getBooksByTypeId();
			} else if (select == 1) {
				this.getBooksById();// 根据图书编号查书
			} else if (select == -1) {// 返回上一层
				break;
			} else if (select == 0) {
				System.out.println("退出系统");
				System.exit(0);
			} else {
				System.out.println("输入有误，请重新输入！");
			}
		}
	}

	// reader登录
	private Reader readerLogin() {
		Integer rid = this.ui.getInt("请输入读者编号：");
		String password = this.ui.getString("请输入密码：");
		return this.totalService.readLogin(rid, password);
	}

	// 修改reader登录密码
	private void loginPassword(Reader reader) {
		System.out.println("------修改密码-------");
		String oldpwd = this.ui.getString("请输入旧密码：");
		int i = 0;
		if (oldpwd.equals(reader.getPassword())) {
			while (true && i < 3) {
				String pwd = this.ui.getString("请输入新密码:");
				String pwdRepeat = this.ui.getString("请再次输入密码:");
				if (pwd.equals(pwdRepeat)) {
					reader.setPassword(pwdRepeat);
					boolean flag = totalService.updateReader(reader);
					if (flag) {
						System.out.println("修改成功!");
					} else {
						System.out.println("修改失败!");
					}
					break;
				} else {
					System.out.println("两次输入的密码不一致!");
					i++;
				}
			}
		}
		// totalService.;
		// System.out.println("原始密码为："+);
	}

	// 查询所有图书
	private void getAllBooks() {
		List<Book> books = this.totalService.getAllBooks();

		System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
		for (Book book : books) {
			// System.out.println(book);
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());

		}
	}

	// 根据书名查询图书
	private void getBooksByName() {
		String bname = this.ui.getString("请输入书名：");
		List<Book> books = this.totalService.getBooksByName(bname);
		if (books == null) {
			System.out.println("馆内没有此书");
		} else {
			System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
			for (Book book : books) {
				// System.out.println(book);
				System.out.println(book.getBid() + "\t\t" + book.getBtid()
						+ "\t\t" + book.getBname() + "\t\t" + book.getPublish()
						+ "\t" + book.getAuthor() + "\t\t" + book.getBnumber()
						+ "\t\t" + book.getPrice());

			}
		}
	}

	// 根据图书编号查询图书
	private void getBooksById() {
		int bid = this.ui.getInt("请输入图书编号bid:");
		Book book = this.totalService.getBooksById(bid);
		if (book == null) {
			System.out.println("没有此书");
		} else {
			// System.out.println(book);
			System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
			System.out.println(book.getBid() + "\t\t" + book.getBtid() + "\t\t"
					+ book.getBname() + "\t\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t\t" + book.getBnumber() + "\t\t"
					+ book.getPrice());

		}
	}

	// 根据图书类型查询图书
	private List<Book> getBooksByTypeId() {
		Integer typeId = this.ui.getInt("请输入图书类型编号：");
		List<Book> books = this.totalService.getBooksByTypeId(typeId);
		System.out.println("图书编号\t\t图书类型编号\t图书名称\t\t出版社\t\t作者\t\t数量\t\t价格");
		for (Book book : books) {
			// System.out.println(book);
			System.out.println(book.getBid() + "\t" + book.getBtid() + "\t"
					+ book.getBname() + "\t" + book.getPublish() + "\t"
					+ book.getAuthor() + "\t" + book.getBnumber() + "\t"
					+ book.getPrice());

		}
		return books;
	}

	// 查询管理员
	private void getAllUser() {
		List<User> Users = this.totalService.getAllUser();
		if (Users == null) {
			System.out.println("没有此管理员");
		} else {
			System.out.println("管理员编号\t\t管理员姓名");
			for (User user : Users) {
				// System.out.println(user);
				System.out.println(user.getMid() + "\t" + user.getUname());

			}
		}
	}

	// 添加管理员
	private void addUser() {
		System.out.println("***********管理员信息录入***********");
		System.out.println("---------------------------------");
		User user = new User();
		String uname = this.ui.getString("请输入管理员姓名:");
		String password = this.ui.getString("请输入管理员密码:");
		user.setUname(uname);
		user.setPassword(password);
		boolean flag = this.totalService.addUser(user);
		if (flag) {
			System.out.println("添加成功！");
		} else {
			System.out.println("添加失败！");
		}
	}

	// 修改管理员信息
	private void updateUser() {
		System.out.println("-------修改管理员信息---------");
		int id = this.ui.getInt("请输入要修改的管理员的编号:");
		User userById = this.totalService.getUserById(id);
		if (userById == null) {
			System.out.println("没有此管理员");
		} else {
			User user = new User();
			String uname = this.ui.getString("请输入管理员姓名:");
			String password = this.ui.getString("请输入管理员密码:");
			boolean flag = this.totalService.updateUser(user);
			if (flag) {
				System.out.println("修改成功！");
			} else {
				System.out.println("修改失败！");
			}
		}
	}

	// 删除管理员
	private void deleteUser() {
		System.out.println("-------删除管理员---------");
		int uid = this.ui.getInt("请输入要删除的管理员的编号:");
		User userById = this.totalService.getUserById(uid);
		if (userById == null) {
			System.out.println("管理员不存在");
		}
		if ("y".equals(this.ui.getString("是否确认删除?(y/n):"))) {
			boolean flag = this.totalService.deleteUser(uid);
			if (flag) {
				System.out.println("删除成功！");
			} else {
				System.out.println("删除失败！");
			}
		} else {
			System.out.println("删除取消");
		}
	}
}
