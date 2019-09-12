package common.oreilly.servlet.multipart;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.semi.member.model.vo.Member;


public class AblingFileRenamePolicy implements FileRenamePolicy{
	private String id;
	public AblingFileRenamePolicy(String id) {
		this.id=id;
	}
	public File rename(File f) {
	
		
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		// file rename : Abling_현재날짜(yyyyMMddHHmmss)_회원아이디
		String uniqueFileName = "Abling_" + simDf.format(new Date(currentTime))+"_"+id;

		String name = f.getName();
		String body = null;
		String ext = null;

		// 앞에것과 동일하게 점(.)을 기준으로 파일명과 확장자를 분리하고
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);  // includes "."
		}
		else {
			body = name;
			ext = "";
		}

		// 분리한파일명대신 유니크한 파일명과 확장자를 조합해서 임시로 파일명을 만든 다음에 파일생성을 시도한다.
		// 파일네임 + . (확장자)
		String tempName = uniqueFileName + ext;
		f = new File(f.getParent(), tempName);
		if (createNewFile(f)) {
			return f;
		}

		// 성공하면 그대로 끝내고 실패하면 루프를 돌면서 파일생성가능할때까지 카운트를 올려서 유니크한 파일명_카운트.확장자형태가 되도록 만든다.
		int count = 0;
		while (!createNewFile(f) && count < 9999) {
			count++;
			String newName = uniqueFileName + "_" + count + ext;
			f = new File(f.getParent(), newName);
		}

		return f;
	}

	private boolean createNewFile(File f) {
		try {
			return f.createNewFile();
		}
		catch (IOException ignored) {
			return false;
		}
	}
}


